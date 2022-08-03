package uz.upay.transactionmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.upay.transactionmanagement.entity.Region;
import uz.upay.transactionmanagement.entity.User;
import uz.upay.transactionmanagement.ref.UserRole;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> findByPhoneNumber(String phoneNumber);

    List<User> findAllByUserRole(UserRole userRole);

    @Query(value = "SELECT * FROM  users WHERE id in ?1 ORDER BY name",nativeQuery = true)
    List<User> findAllByIdOrderByName(List<Long> id);

    @Query(value = "select courier_id from courier_regions where region_id=?1",nativeQuery = true)
    List<Long> getCarriersForRegion (Long courierId);



}
