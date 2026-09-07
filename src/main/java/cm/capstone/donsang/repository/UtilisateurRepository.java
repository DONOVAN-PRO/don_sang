package cm.capstone.donsang.repository;
import cm.capstone.donsang.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    Optional<Utilisateur> findByEmail(String email);
    long countByRole(cm.capstone.donsang.model.Enums.Role role);
}
