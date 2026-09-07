package cm.capstone.donsang.service;

import cm.capstone.donsang.dto.AuthDtos.*;
import cm.capstone.donsang.model.*;
import cm.capstone.donsang.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.*;
import java.util.Random;
import static cm.capstone.donsang.model.Enums.*;

@Service @RequiredArgsConstructor
public class AuthService {
    private final UtilisateurRepository users;
    private final ProfilDonneurRepository donneurs;
    private final ProfilEtablissementRepository etablissements;
    private final CodeActivationRepository codes;
    private final PasswordEncoder encoder;
    private final EmailService email;

    @Transactional
    public MessageResponse inscrire(InscriptionRequest r) {
        if(users.findByEmail(r.email()).isPresent()) throw new IllegalArgumentException("Email déjà utilisé");
        Role role=Role.valueOf(r.role().toUpperCase());
        if(role==Role.COORDINATEUR) throw new IllegalArgumentException("Le rôle coordinateur est réservé");
        Utilisateur u=Utilisateur.builder().nom(r.nom()).email(r.email()).motDePasse(encoder.encode(r.motDePasse()))
                .methodeConnexion(MethodeConnexion.EMAIL).role(role).compteActif(false)
                .dateCreation(LocalDateTime.now()).build();
        users.save(u);

        if(role==Role.DONNEUR) {
            if(r.groupeSanguin()==null || r.groupeSanguin().isBlank()) throw new IllegalArgumentException("Groupe sanguin obligatoire");
            donneurs.save(ProfilDonneur.builder().utilisateur(u).groupeSanguin(r.groupeSanguin())
                    .ville(r.ville()).telephone(r.telephone()).build());
        } else {
            etablissements.save(ProfilEtablissement.builder().utilisateur(u).nomEtablissement(r.nomEtablissement())
                    .typeEtablissement(TypeEtablissement.valueOf(r.typeEtablissement().toUpperCase()))
                    .ville(r.ville()).adresse(r.adresse()).telephone(r.telephone())
                    .statutValidation(StatutValidation.EN_ATTENTE).build());
        }
        envoyerCode(u);
        return new MessageResponse("Inscription réussie. Vérifiez votre email avec le code reçu.");
    }

    @Transactional
    public void envoyerCode(Utilisateur u) {
        String code=String.format("%06d",new Random().nextInt(1_000_000));
        codes.save(CodeActivation.builder().utilisateur(u).code(code).dateGeneration(LocalDateTime.now())
                .dateExpiration(LocalDateTime.now().plusMinutes(15)).utilise(false).build());
        email.sendActivation(u.getEmail(),u.getNom(),code,LocalDateTime.now().plusMinutes(15));
    }

    @Transactional
    public MessageResponse verifier(CodeRequest r) {
        Utilisateur u=users.findByEmail(r.email()).orElseThrow(()->new IllegalArgumentException("Compte introuvable"));
        CodeActivation c=codes.findTopByUtilisateurIdAndUtiliseFalseOrderByDateGenerationDesc(u.getId())
                .orElseThrow(()->new IllegalArgumentException("Aucun code actif"));
        if(c.getDateExpiration().isBefore(LocalDateTime.now())) throw new IllegalArgumentException("Code expiré");
        if(!c.getCode().equals(r.code())) throw new IllegalArgumentException("Code incorrect");
        c.setUtilise(true); u.setCompteActif(true); users.save(u);
        return new MessageResponse("Compte activé avec succès.");
    }

    @Transactional
    public MessageResponse renvoyer(String email) {
        Utilisateur u=users.findByEmail(email).orElseThrow(()->new IllegalArgumentException("Compte introuvable"));
        envoyerCode(u); return new MessageResponse("Nouveau code envoyé.");
    }
}
