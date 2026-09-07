package cm.capstone.donsang.controller;
import cm.capstone.donsang.dto.BusinessDtos.ProfilRequest;
import cm.capstone.donsang.dto.ResponseDtos.*;
import cm.capstone.donsang.repository.*;
import cm.capstone.donsang.service.DtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/profil") @RequiredArgsConstructor
public class ProfilController {
 private final UtilisateurRepository users; private final ProfilDonneurRepository donneurs; private final ProfilEtablissementRepository etablissements; private final DtoMapper mapper;
 @GetMapping public Object profil(Authentication a){var u=users.findByEmail(a.getName()).orElseThrow();return u.getRole().name().equals("DONNEUR")?mapper.donneur(donneurs.findByUtilisateurId(u.getId()).orElseThrow()):u.getRole().name().equals("ETABLISSEMENT")?mapper.etablissement(etablissements.findByUtilisateurId(u.getId()).orElseThrow()):mapper.user(u);}
 @PutMapping public Object modifier(Authentication a,@Valid @RequestBody ProfilRequest r){
  var u=users.findByEmail(a.getName()).orElseThrow(); if(r.nom()!=null&&!r.nom().isBlank())u.setNom(r.nom());users.save(u);
  if(u.getRole().name().equals("DONNEUR")){var d=donneurs.findByUtilisateurId(u.getId()).orElseThrow();if(r.ville()!=null)d.setVille(r.ville());if(r.telephone()!=null)d.setTelephone(r.telephone());if(r.groupeSanguin()!=null)d.setGroupeSanguin(r.groupeSanguin());return mapper.donneur(donneurs.save(d));}
  if(u.getRole().name().equals("ETABLISSEMENT")){var e=etablissements.findByUtilisateurId(u.getId()).orElseThrow();if(r.nomEtablissement()!=null)e.setNomEtablissement(r.nomEtablissement());if(r.ville()!=null)e.setVille(r.ville());if(r.adresse()!=null)e.setAdresse(r.adresse());if(r.telephone()!=null)e.setTelephone(r.telephone());return mapper.etablissement(etablissements.save(e));}
  return mapper.user(u);
 }
}
