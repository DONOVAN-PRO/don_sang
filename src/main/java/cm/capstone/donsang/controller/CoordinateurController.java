package cm.capstone.donsang.controller;
import cm.capstone.donsang.dto.BusinessDtos.AlerteRequest; import cm.capstone.donsang.dto.ResponseDtos.*; import cm.capstone.donsang.model.Enums.Role; import cm.capstone.donsang.repository.*; import cm.capstone.donsang.service.CoordinateurService; import jakarta.validation.Valid; import lombok.RequiredArgsConstructor; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/coordinateur") @RequiredArgsConstructor public class CoordinateurController {
 private final CoordinateurService service; private final UtilisateurRepository users; private final ProfilEtablissementRepository etablissements; private final ProfilDonneurRepository donneurs; private final DemandeUrgenteRepository demandes; private final DonRepository dons; private final cm.capstone.donsang.service.DtoMapper mapper;
 @GetMapping("/tableau-de-bord") public Object dashboard(){return service.dashboard();}
 @GetMapping("/utilisateurs") public Object utilisateurs(){return users.findAll().stream().map(mapper::user).toList();}
 @PutMapping("/etablissements/{id}/valider") public void valider(@PathVariable Long id){service.valider(id,true);}
 @PutMapping("/etablissements/{id}/rejeter") public void rejeter(@PathVariable Long id){service.valider(id,false);}
 @PutMapping("/utilisateurs/{id}/desactiver") public void desactiver(@PathVariable Long id){service.desactiver(id);}
 @GetMapping("/demandes-urgentes") public Object demandes(){return demandes.findAll().stream().map(mapper::demande).toList();}
 @PostMapping("/alertes") public void alerte(@Valid @RequestBody AlerteRequest r){service.alerte(r);}
}
