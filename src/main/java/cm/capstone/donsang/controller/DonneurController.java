package cm.capstone.donsang.controller;
import cm.capstone.donsang.dto.BusinessDtos.*; import cm.capstone.donsang.dto.ResponseDtos.*; import cm.capstone.donsang.service.DonService; import cm.capstone.donsang.service.DtoMapper;
import jakarta.validation.Valid; import lombok.RequiredArgsConstructor; import org.springframework.web.bind.annotation.*; import java.security.Principal;
@RestController @RequestMapping("/api/donneur") @RequiredArgsConstructor public class DonneurController {
 private final DonService service; private final DtoMapper mapper;
 @GetMapping("/tableau-de-bord") public Object dashboard(Principal p){return service.dashboardDonneur(p.getName());}
 @GetMapping("/etablissements") public Object etablissements(){return service.etablissementsValides().stream().map(mapper::etablissement).toList();}
 @GetMapping("/demandes-urgentes") public Object demandes(Principal p){return service.demandes(p.getName()).stream().map(mapper::demande).toList();}
 @PostMapping("/rendez-vous") public Object rdv(Principal p,@Valid @RequestBody RendezVousRequest r){return mapper.rdv(service.prendreRdv(p.getName(),r));}
 @DeleteMapping("/rendez-vous/{id}") public void annuler(Principal p,@PathVariable Long id){service.annuler(p.getName(),id);}
 @GetMapping("/dons") public Object dons(Principal p){return service.historique(p.getName()).stream().map(mapper::don).toList();}
}
