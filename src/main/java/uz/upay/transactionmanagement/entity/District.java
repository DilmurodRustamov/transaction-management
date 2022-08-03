package uz.upay.transactionmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameUz;
    private String nameRu;
    private String nameEn;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Region region;

    public District(String nameUz, String nameRu, String nameEn, Region region) {
        this.nameUz = nameUz;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
        this.region = region;
    }
}
