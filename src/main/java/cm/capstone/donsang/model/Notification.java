package cm.capstone.donsang.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import static cm.capstone.donsang.model.Enums.*;

@Entity @Table(name="notifications")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Notification {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, columnDefinition="TEXT") private String message;
    @Enumerated(EnumType.STRING) private TypeNotification type;
    private boolean lue;
    private LocalDateTime dateEnvoi;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="utilisateur_id")
    private Utilisateur utilisateur;
}
