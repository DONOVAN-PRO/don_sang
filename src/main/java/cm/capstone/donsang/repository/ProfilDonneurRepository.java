package cm.capstone.donsang.repository;
import cm.capstone.donsang.model.ProfilDonneur; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface ProfilDonneurRepository extends JpaRepository<ProfilDonneur,Long>{Optional<ProfilDonneur> findByUtilisateurId(Long id); long countByGroupeSanguin(String groupeSanguin);}
