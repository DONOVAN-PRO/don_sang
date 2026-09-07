package cm.capstone.donsang.repository;
import cm.capstone.donsang.model.StockSang;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface StockSangRepository extends JpaRepository<StockSang,Long> {
    List<StockSang> findByEtablissementId(Long id);
    Optional<StockSang> findByEtablissementIdAndGroupeSanguin(Long id,String groupe);
}
