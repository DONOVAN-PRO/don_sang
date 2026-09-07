package cm.capstone.donsang.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import static cm.capstone.donsang.model.Enums.*;

@Entity @Table(name="utilisateurs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Utilisateur {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false) private String nom;
    @Column(nullable=false, unique=true) private String email;
    private String motDePasse;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private MethodeConnexion methodeConnexion;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private Role role;
    private boolean compteActif;
    private LocalDateTime dateCreation;
    private LocalDateTime derniereConnexion;
}
