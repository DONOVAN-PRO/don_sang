package cm.capstone.donsang.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import static cm.capstone.donsang.model.Enums.*;

@Entity @Table(name="historiques_emails")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class HistoriqueEmail {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false) private String destinataire;
    private String sujet;
    @Column(columnDefinition="TEXT") private String contenu;
    @Enumerated(EnumType.STRING) private TypeEmail type;
    @Enumerated(EnumType.STRING) private StatutEmail statut;
    private LocalDateTime dateEnvoi;
}
