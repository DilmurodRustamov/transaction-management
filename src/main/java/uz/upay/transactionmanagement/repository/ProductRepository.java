package uz.upay.transactionmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.upay.transactionmanagement.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

    boolean existsById(Long id);

    boolean existsByName(String name);
}
