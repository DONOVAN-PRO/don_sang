package cm.capstone.donsang.dto;
import jakarta.validation.constraints.*;
import java.time.*;
public final class BusinessDtos {
    private BusinessDtos() {}
    public record RendezVousRequest(@NotNull Long etablissementId,@NotNull LocalDate dateRendezVous,
                                    @NotBlank String creneauHoraire,Long demandeId) {}
    public record StockRequest(@Min(0) int quantitePoches) {}
    public record DemandeRequest(@NotBlank String groupeSanguinRecherche,@Min(1) int quantiteNecessaire,
                                 @NotBlank String niveauUrgence,String description,@NotNull LocalDateTime dateLimite) {}
    public record DonRequest(@NotNull Long rendezVousId,@Min(1) int quantiteMl,@NotBlank String groupeSanguin) {}
    public record AlerteRequest(@NotBlank String groupeSanguin,@NotBlank String message) {}
    public record ProfilRequest(String nom,String ville,String telephone,String adresse,
                                String groupeSanguin,String nomEtablissement) {}
}
