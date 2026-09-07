package cm.capstone.donsang.repository;
import cm.capstone.donsang.model.RendezVousDon;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface RendezVousDonRepository extends JpaRepository<RendezVousDon,Long> {
    List<RendezVousDon> findByDonneurId(Long id);
    List<RendezVousDon> findByEtablissementId(Long id);
    long countByEtablissementIdAndDateRendezVousBetween(Long id, java.time.LocalDate a, java.time.LocalDate b);
}
