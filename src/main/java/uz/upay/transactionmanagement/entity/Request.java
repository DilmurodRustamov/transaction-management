package uz.upay.transactionmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = District.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id", updatable = false, insertable = false, referencedColumnName = "id")
    private District district;

    @Column(name = "district_id")
    private Long districtId;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", updatable = false, insertable = false, referencedColumnName = "id")
    private Product product;

    @Column(name = "product_id")
    private Long productId;

    @Column(nullable = false)
    private String placeName;

    private Long externalId;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false, insertable = false, referencedColumnName = "id")
    private User user;

    @Column(name = "user_id")
    private Long requestedBy;

    private Long deliveredBy;
}
