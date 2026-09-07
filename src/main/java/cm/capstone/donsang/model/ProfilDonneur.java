package cm.capstone.donsang.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity @Table(name="profils_donneurs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProfilDonneur {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false) private String groupeSanguin;
    private LocalDate dateNaissance;
    private String ville;
    private String telephone;
    private LocalDate dateDernierDon;
    @OneToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="utilisateur_id", unique=true)
    private Utilisateur utilisateur;
}
