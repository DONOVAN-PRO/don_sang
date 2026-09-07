package cm.capstone.donsang.model;

import jakarta.persistence.*;
import lombok.*;
import static cm.capstone.donsang.model.Enums.*;

@Entity @Table(name="profils_etablissements")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProfilEtablissement {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false) private String nomEtablissement;
    @Enumerated(EnumType.STRING) private TypeEtablissement typeEtablissement;
    private String ville;
    private String adresse;
    private String telephone;
    @Enumerated(EnumType.STRING) private StatutValidation statutValidation;
    @OneToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="utilisateur_id", unique=true)
    private Utilisateur utilisateur;
}
