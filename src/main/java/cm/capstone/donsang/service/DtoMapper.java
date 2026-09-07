package cm.capstone.donsang.service;

import cm.capstone.donsang.dto.ResponseDtos.*;
import cm.capstone.donsang.model.*;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {
 public UserDto user(Utilisateur u){return new UserDto(u.getId(),u.getNom(),u.getEmail(),u.getMethodeConnexion(),u.getRole(),u.isCompteActif(),u.getDateCreation(),u.getDerniereConnexion());}
 public DonneurDto donneur(ProfilDonneur d){var u=d.getUtilisateur();return new DonneurDto(d.getId(),u.getNom(),u.getEmail(),d.getGroupeSanguin(),d.getDateNaissance(),d.getVille(),d.getTelephone(),d.getDateDernierDon());}
 public EtablissementDto etablissement(ProfilEtablissement e){var u=e.getUtilisateur();return new EtablissementDto(e.getId(),u.getNom(),u.getEmail(),e.getNomEtablissement(),e.getTypeEtablissement(),e.getVille(),e.getAdresse(),e.getTelephone(),e.getStatutValidation());}
 public StockDto stock(StockSang s){return new StockDto(s.getId(),s.getGroupeSanguin(),s.getQuantitePoches(),s.getDateMiseAJour());}
 public DemandeDto demande(DemandeUrgente d){return new DemandeDto(d.getId(),d.getGroupeSanguinRecherche(),d.getQuantiteNecessaire(),d.getNiveauUrgence(),d.getDescription(),d.getDateCreation(),d.getDateLimite(),d.getStatut(),d.getEtablissement().getId(),d.getEtablissement().getNomEtablissement());}
 public RendezVousDto rdv(RendezVousDon r){return new RendezVousDto(r.getId(),r.getDateRendezVous(),r.getCreneauHoraire(),r.getStatut(),r.getDonneur().getId(),r.getDonneur().getUtilisateur().getNom(),r.getEtablissement().getId(),r.getEtablissement().getNomEtablissement(),r.getDemande()==null?null:r.getDemande().getId());}
 public DonDto don(Don d){return new DonDto(d.getId(),d.getDateDon(),d.getQuantiteMl(),d.getGroupeSanguin(),d.getStatutValidation(),d.getRendezVous()==null?null:d.getRendezVous().getId(),d.getDonneur().getId(),d.getEtablissement().getId());}
 public NotificationDto notification(Notification n){return new NotificationDto(n.getId(),n.getMessage(),n.getType(),n.isLue(),n.getDateEnvoi());}
}
