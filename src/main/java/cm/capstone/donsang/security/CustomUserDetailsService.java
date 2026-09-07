package cm.capstone.donsang.security;

import cm.capstone.donsang.model.Utilisateur;
import cm.capstone.donsang.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UtilisateurRepository repository;
    @Override public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur u=repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));
        return User.withUsername(u.getEmail())
                .password(u.getMotDePasse()==null ? "{noop}GOOGLE" : u.getMotDePasse())
                .roles(u.getRole().name())
                .disabled(!u.isCompteActif()).build();
    }
}
