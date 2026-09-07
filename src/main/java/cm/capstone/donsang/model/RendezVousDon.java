package cm.capstone.donsang.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import static cm.capstone.donsang.model.Enums.*;

@Entity @Table(name="rendez_vous_dons")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RendezVousDon {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private LocalDate dateRendezVous;
    private String creneauHoraire;
    @Enumerated(EnumType.STRING) private StatutRendezVous statut;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="donneur_id")
    private ProfilDonneur donneur;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="etablissement_id")
    private ProfilEtablissement etablissement;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="demande_id")
    private DemandeUrgente demande;
}
