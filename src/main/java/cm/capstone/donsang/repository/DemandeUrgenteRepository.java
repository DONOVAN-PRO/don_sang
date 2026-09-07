package cm.capstone.donsang.repository;
import cm.capstone.donsang.model.DemandeUrgente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.*;
import static cm.capstone.donsang.model.Enums.*;
public interface DemandeUrgenteRepository extends JpaRepository<DemandeUrgente,Long> {
    List<DemandeUrgente> findByStatutAndGroupeSanguinRecherche(StatutDemande statut,String groupe);
    List<DemandeUrgente> findByEtablissementId(Long id);
    long countByStatut(StatutDemande statut);
    List<DemandeUrgente> findByStatutAndDateLimiteBefore(StatutDemande statut, LocalDateTime date);
}
