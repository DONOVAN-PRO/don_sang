package cm.capstone.donsang.service;
import cm.capstone.donsang.model.Utilisateur;
import org.springframework.stereotype.Service;
@Service
public class HistoriqueService {
    public String role(Utilisateur u){ return u.getRole().name(); }
}
