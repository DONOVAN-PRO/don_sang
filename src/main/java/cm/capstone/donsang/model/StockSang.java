package cm.capstone.donsang.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name="stocks_sang", uniqueConstraints=@UniqueConstraint(columnNames={"etablissement_id","groupeSanguin"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class StockSang {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false) private String groupeSanguin;
    private int quantitePoches;
    private LocalDateTime dateMiseAJour;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="etablissement_id")
    private ProfilEtablissement etablissement;
}
