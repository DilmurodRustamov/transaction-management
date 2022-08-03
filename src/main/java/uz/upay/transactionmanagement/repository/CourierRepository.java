package uz.upay.transactionmanagement.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.upay.transactionmanagement.entity.Courier;
import uz.upay.transactionmanagement.entity.Region;

import java.util.List;

public interface CourierRepository extends JpaRepository<Courier,Long> {

    @Query("select u from Region u")
    List<Region> findAllRegions(Sort sort);

    @Query(value = "select * from courier_regions where region_id=?1",nativeQuery = true)
    List<Region> getOrderByRegion(Long courierId);

    @Query(value = "select region_id from courier_regions where courier_id=?1",nativeQuery = true)
    List<Long> getRegionIds(Long courierId);

}
