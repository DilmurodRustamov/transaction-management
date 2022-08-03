package uz.upay.transactionmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nameUz;

    @Column(nullable = false)
    private String nameRu;

    @Column(nullable = false)
    private String nameEn;

    private Integer transactionCount;

    @OrderBy(value = "nameUz asc,nameEn asc ")
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private List<District> districts;

    @ManyToMany(mappedBy = "regions", fetch = FetchType.LAZY)
    private List<User> couriers = new ArrayList<>();

}
