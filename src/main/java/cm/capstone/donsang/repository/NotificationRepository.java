package cm.capstone.donsang.repository;
import cm.capstone.donsang.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findByUtilisateurIdOrderByDateEnvoiDesc(Long id);
}
