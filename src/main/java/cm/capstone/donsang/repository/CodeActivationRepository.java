package cm.capstone.donsang.repository;
import cm.capstone.donsang.model.CodeActivation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface CodeActivationRepository extends JpaRepository<CodeActivation,Long> {
    Optional<CodeActivation> findTopByUtilisateurIdAndUtiliseFalseOrderByDateGenerationDesc(Long utilisateurId);
}
