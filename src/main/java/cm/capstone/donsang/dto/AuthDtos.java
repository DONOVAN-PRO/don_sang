package cm.capstone.donsang.dto;
import jakarta.validation.constraints.*;
public final class AuthDtos {
    private AuthDtos() {}
    public record InscriptionRequest(
        @NotBlank String nom, @Email @NotBlank String email,
        @Size(min=8) String motDePasse,
        @NotNull String role,
        String groupeSanguin, String ville, String telephone,
        String nomEtablissement, String typeEtablissement, String adresse) {}
    public record CodeRequest(@Email @NotBlank String email, @NotBlank String code) {}
    public record ConnexionRequest(@Email @NotBlank String email, @NotBlank String motDePasse) {}
    public record MessageResponse(String message) {}
}
