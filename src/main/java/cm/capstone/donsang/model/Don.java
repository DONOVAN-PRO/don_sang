package cm.capstone.donsang.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import static cm.capstone.donsang.model.Enums.*;

@Entity @Table(name="dons")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Don {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private LocalDate dateDon;
    private int quantiteMl;
    private String groupeSanguin;
    @Enumerated(EnumType.STRING) private StatutDon statutValidation;
    @OneToOne(fetch=FetchType.LAZY) @JoinColumn(name="rendez_vous_id", unique=true)
    private RendezVousDon rendezVous;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="donneur_id")
    private ProfilDonneur donneur;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="etablissement_id")
    private ProfilEtablissement etablissement;
}
