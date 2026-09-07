package cm.capstone.donsang.dto;

import java.time.*;
import java.util.*;
import static cm.capstone.donsang.model.Enums.*;

public final class ResponseDtos {
 private ResponseDtos() {}
 public record UserDto(Long id,String nom,String email,MethodeConnexion methodeConnexion,Role role,boolean compteActif,LocalDateTime dateCreation,LocalDateTime derniereConnexion) {}
 public record DonneurDto(Long id,String nom,String email,String groupeSanguin,LocalDate dateNaissance,String ville,String telephone,LocalDate dateDernierDon) {}
 public record EtablissementDto(Long id,String nomUtilisateur,String email,String nomEtablissement,TypeEtablissement typeEtablissement,String ville,String adresse,String telephone,StatutValidation statutValidation) {}
 public record StockDto(Long id,String groupeSanguin,int quantitePoches,LocalDateTime dateMiseAJour) {}
 public record DemandeDto(Long id,String groupeSanguinRecherche,int quantiteNecessaire,NiveauUrgence niveauUrgence,String description,LocalDateTime dateCreation,LocalDateTime dateLimite,StatutDemande statut,Long etablissementId,String nomEtablissement) {}
 public record RendezVousDto(Long id,LocalDate dateRendezVous,String creneauHoraire,StatutRendezVous statut,Long donneurId,String nomDonneur,Long etablissementId,String nomEtablissement,Long demandeId) {}
 public record DonDto(Long id,LocalDate dateDon,int quantiteMl,String groupeSanguin,StatutDon statutValidation,Long rendezVousId,Long donneurId,Long etablissementId) {}
 public record NotificationDto(Long id,String message,TypeNotification type,boolean lue,LocalDateTime dateEnvoi) {}
 public record DonneurDashboard(long totalDons,String prochainDonPossible,String eligibilite,long viesPotentiellementSauvees,long demandesActives) {}
 public record EtablissementDashboard(List<StockDto> stock,long demandesActives,long rdvSemaine,long donsMois) {}
 public record CoordinateurDashboard(long totalDonneurs,long etablissementsActifs,long donsReseau,long demandesOuvertes,Map<String,Long> repartitionGroupes) {}
}
