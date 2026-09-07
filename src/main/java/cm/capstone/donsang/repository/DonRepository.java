package cm.capstone.donsang.repository;
import cm.capstone.donsang.model.Don;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.*;
public interface DonRepository extends JpaRepository<Don,Long> {
    List<Don> findByDonneurIdOrderByDateDonDesc(Long id);
    long countByDonneurIdAndStatutValidation(Long id, cm.capstone.donsang.model.Enums.StatutDon statut);
    long countByEtablissementIdAndDateDonBetween(Long id, LocalDate a, LocalDate b);
    long countByStatutValidation(cm.capstone.donsang.model.Enums.StatutDon statut);
}
