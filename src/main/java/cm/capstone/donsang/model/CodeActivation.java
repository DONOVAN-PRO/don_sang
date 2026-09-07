package cm.capstone.donsang.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name="codes_activation")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CodeActivation {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false) private String code;
    private LocalDateTime dateGeneration;
    private LocalDateTime dateExpiration;
    private boolean utilise;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) private Utilisateur utilisateur;
}
