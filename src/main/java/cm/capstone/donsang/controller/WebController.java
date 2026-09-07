package cm.capstone.donsang.controller;
import cm.capstone.donsang.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
@Controller @RequiredArgsConstructor
public class WebController {
    private final UtilisateurRepository users;
    @GetMapping("/") public String home(){return "index";}
    @GetMapping("/login") public String login(){return "login";}
    @GetMapping("/inscription") public String inscription(){return "inscription";}
    @GetMapping("/verification") public String verification(){return "verification";}
    @GetMapping("/profil") public String profil(){return "profil";}
    @GetMapping("/dashboard") public String dashboard(Principal p,Model m){
        var u=users.findByEmail(p.getName()).orElseThrow(); m.addAttribute("user",u);
        return switch(u.getRole().name()){case "DONNEUR"->"dashboard-donneur";case "ETABLISSEMENT"->"dashboard-etablissement";default->"dashboard-coordinateur";};
    }
}
