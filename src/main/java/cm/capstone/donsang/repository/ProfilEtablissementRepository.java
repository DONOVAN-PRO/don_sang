package cm.capstone.donsang.repository;
import cm.capstone.donsang.model.ProfilEtablissement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import static cm.capstone.donsang.model.Enums.*;
public interface ProfilEtablissementRepository extends JpaRepository<ProfilEtablissement,Long> {
    Optional<ProfilEtablissement> findByUtilisateurId(Long id);
    long countByStatutValidation(StatutValidation statut);
}
