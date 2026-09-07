package cm.capstone.donsang.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import static cm.capstone.donsang.model.Enums.*;

@Entity @Table(name="demandes_urgentes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DemandeUrgente {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false) private String groupeSanguinRecherche;
    private int quantiteNecessaire;
    @Enumerated(EnumType.STRING) private NiveauUrgence niveauUrgence;
    @Column(columnDefinition="TEXT") private String description;
    private LocalDateTime dateCreation;
    private LocalDateTime dateLimite;
    @Enumerated(EnumType.STRING) private StatutDemande statut;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="etablissement_id")
    private ProfilEtablissement etablissement;
}
