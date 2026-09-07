package cm.capstone.donsang.controller;

import cm.capstone.donsang.dto.AuthDtos.*;
import cm.capstone.donsang.dto.ResponseDtos.UserDto;
import cm.capstone.donsang.repository.UtilisateurRepository;
import cm.capstone.donsang.service.AuthService;
import cm.capstone.donsang.service.DtoMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/auth") @RequiredArgsConstructor
public class AuthController {
 private final AuthService service; private final AuthenticationManager manager; private final UtilisateurRepository users; private final DtoMapper mapper;
 @PostMapping("/inscription") public ResponseEntity<?> inscription(@Valid @RequestBody InscriptionRequest r){try{return ResponseEntity.ok(service.inscrire(r));}catch(Exception e){return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));}}
 @PostMapping("/verification-code") public ResponseEntity<?> verification(@Valid @RequestBody CodeRequest r){try{return ResponseEntity.ok(service.verifier(r));}catch(Exception e){return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));}}
 @PostMapping("/renvoi-code") public ResponseEntity<?> renvoi(@RequestParam String email){try{return ResponseEntity.ok(service.renvoyer(email));}catch(Exception e){return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));}}
 @PostMapping("/connexion") public ResponseEntity<?> connexion(@Valid @RequestBody ConnexionRequest r,HttpServletRequest request,HttpServletResponse response){
  try { var u=users.findByEmail(r.email()).orElseThrow(()->new BadCredentialsException("Identifiants incorrects"));
   if(!u.isCompteActif()) return ResponseEntity.status(403).body(new MessageResponse("Compte non activé"));
   var auth=manager.authenticate(new UsernamePasswordAuthenticationToken(r.email(),r.motDePasse()));
   SecurityContextHolder.getContext().setAuthentication(auth); new HttpSessionSecurityContextRepository().saveContext(SecurityContextHolder.getContext(),request,response);
   u.setDerniereConnexion(java.time.LocalDateTime.now()); users.save(u); return ResponseEntity.ok(mapper.user(u));
  } catch(AuthenticationException e){return ResponseEntity.status(401).body(new MessageResponse("Email ou mot de passe incorrect"));}
 }
 @GetMapping("/connexion-google") public org.springframework.web.servlet.view.RedirectView google(){return new org.springframework.web.servlet.view.RedirectView("/oauth2/authorization/google");}
 @GetMapping("/callback-google") public org.springframework.web.servlet.view.RedirectView callback(){return new org.springframework.web.servlet.view.RedirectView("/dashboard");}
 @PostMapping("/deconnexion") public ResponseEntity<?> deconnexion(HttpServletRequest request){request.getSession(false); SecurityContextHolder.clearContext(); if(request.getSession(false)!=null) request.getSession(false).invalidate(); return ResponseEntity.ok(new MessageResponse("Déconnexion réussie"));}
}
