package cm.capstone.donsang.controller;
import cm.capstone.donsang.dto.BusinessDtos.*; import cm.capstone.donsang.service.*; import jakarta.validation.Valid; import lombok.RequiredArgsConstructor; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/etablissement") @RequiredArgsConstructor public class EtablissementController {
 private final DonService service; private final DtoMapper mapper;
 @GetMapping("/tableau-de-bord") public Object dashboard(java.security.Principal p){return service.dashboardEtablissement(p.getName());}
 @GetMapping("/stock") public Object stock(java.security.Principal p){return service.stock(p.getName()).stream().map(mapper::stock).toList();}
 @PutMapping("/stock/{groupeSanguin}") public Object stock(java.security.Principal p,@PathVariable String groupeSanguin,@Valid @RequestBody StockRequest r){return mapper.stock(service.updateStock(p.getName(),groupeSanguin,r));}
 @PostMapping("/demandes-urgentes") public Object demande(java.security.Principal p,@Valid @RequestBody DemandeRequest r){return mapper.demande(service.creerDemande(p.getName(),r));}
 @PutMapping("/demandes-urgentes/{id}/cloturer") public void cloturer(java.security.Principal p,@PathVariable Long id){service.cloturer(p.getName(),id);}
 @GetMapping("/rendez-vous") public Object rdvs(java.security.Principal p){return service.rdvsEtablissement(p.getName()).stream().map(mapper::rdv).toList();}
 @PutMapping("/rendez-vous/{id}/confirmer") public Object confirmer(java.security.Principal p,@PathVariable Long id){service.confirmerRdv(p.getName(),id);return mapper.rdv(service.rdvsEtablissement(p.getName()).stream().filter(x->x.getId().equals(id)).findFirst().orElseThrow());}
 @PostMapping("/dons") public Object don(java.security.Principal p,@Valid @RequestBody DonRequest r){return mapper.don(service.enregistrerDon(p.getName(),r));}
}
