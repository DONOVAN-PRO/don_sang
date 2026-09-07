package cm.capstone.donsang.config;

import cm.capstone.donsang.model.*;
import cm.capstone.donsang.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static cm.capstone.donsang.model.Enums.*;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

 private final PasswordEncoder encoder;
 private final UtilisateurRepository users;
 private final ProfilDonneurRepository donneurs;
 private final ProfilEtablissementRepository etablissements;

 @Bean
 @Transactional
 CommandLineRunner seed(@Value("${app.seed-test-data:false}") boolean enabled) {
  return args -> {
   if (!enabled) {
    return;
   }

   // 1. Coordinateur
   create("coordinateur@test.com", "Coordinateur Test", "Coord1234!", Role.COORDINATEUR);

   // 2. Donneur
   Utilisateur donneurUser = create("donneur@test.com", "Donneur Test", "Donneur1234!", Role.DONNEUR);
   if (donneurs.findByUtilisateurId(donneurUser.getId()).isEmpty()) {
    donneurs.save(ProfilDonneur.builder()
            .utilisateur(donneurUser)
            .groupeSanguin("O+")
            .ville("Douala")
            .telephone("690000000")
            .build());
   }

   // 3. Établissement
   Utilisateur etabUser = create("etablissement@test.com", "Établissement Test", "Etab1234!", Role.ETABLISSEMENT);
   if (etablissements.findByUtilisateurId(etabUser.getId()).isEmpty()) {
    etablissements.save(ProfilEtablissement.builder()
            .utilisateur(etabUser)
            .nomEtablissement("Hôpital Test")
            .typeEtablissement(TypeEtablissement.HOPITAL)
            .ville("Douala")
            .adresse("Centre-ville")
            .statutValidation(StatutValidation.VALIDE)
            .build());
   }
  };
 }

 private Utilisateur create(String email, String nom, String password, Role role) {
  return users.findByEmail(email).orElseGet(() -> users.save(
          Utilisateur.builder()
                  .email(email)
                  .nom(nom)
                  .motDePasse(encoder.encode(password))
                  .methodeConnexion(MethodeConnexion.EMAIL)
                  .role(role)
                  .compteActif(true)
                  .dateCreation(LocalDateTime.now())
                  .build()
  ));
 }
}