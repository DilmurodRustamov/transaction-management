package uz.upay.transactionmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer score;

    private Integer transactionCount;

    private String regionName;

    @JsonIgnore
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "currier_id", updatable = false, insertable = false, referencedColumnName = "id")
    private User currier;

    @Column(name = "currier_id")
    private Long currierId;

    @JsonIgnore
    @ManyToOne(targetEntity = Request.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id", updatable = false, insertable = false, referencedColumnName = "id")
    private Request request;

    @Column(name = "request_id")
    private Long requestId;

    @JsonIgnore
    @ManyToOne(targetEntity = Region.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", updatable = false, insertable = false, referencedColumnName = "id")
    private Region region;

    @Column(name = "region_id")
    private Long regionId;

    @JsonIgnore
    @ManyToOne(targetEntity = Offer.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id", updatable = false, insertable = false, referencedColumnName = "id")
    private Offer offer;

    @Column(name = "offer_id")
    private Long offerId;

    @JsonIgnore
    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", updatable = false, insertable = false, referencedColumnName = "id")
    private Product product;

    @Column(name = "product_id")
    private Long productId;

    private Long externalId;

}
