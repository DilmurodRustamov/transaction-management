package uz.upay.transactionmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.upay.transactionmanagement.entity.Offer;

public interface OfferRepository extends JpaRepository<Offer,Long> {

}
