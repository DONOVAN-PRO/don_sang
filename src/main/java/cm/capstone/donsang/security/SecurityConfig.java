package cm.capstone.donsang.security;

import cm.capstone.donsang.service.OAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration @RequiredArgsConstructor
public class SecurityConfig {
    private final OAuth2Service oauth2Service;

    @Bean PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }

    @Bean AuthenticationManager authenticationManager(AuthenticationConfiguration c) throws Exception {
        return c.getAuthenticationManager();
    }

    @Bean SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
          .authorizeHttpRequests(auth -> auth
              .requestMatchers("/", "/login", "/inscription", "/verification", "/css/**",
                               "/api/auth/**", "/oauth2/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
              .requestMatchers("/api/donneur/**").hasRole("DONNEUR")
              .requestMatchers("/api/etablissement/**").hasRole("ETABLISSEMENT")
              .requestMatchers("/api/coordinateur/**").hasRole("COORDINATEUR")
              .anyRequest().authenticated())
          .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/dashboard", true).permitAll())
          .oauth2Login(oauth -> oauth.loginPage("/login").successHandler(oauth2Service))
          .logout(logout -> logout.logoutUrl("/api/auth/deconnexion").logoutSuccessUrl("/login?logout").permitAll());
        return http.build();
    }
}
