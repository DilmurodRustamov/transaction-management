package uz.upay.transactionmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.upay.transactionmanagement.entity.Region;
import uz.upay.transactionmanagement.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region,Long> {

    List<Region> findAllByOrderByNameUz();

    Optional<Region> findByNameUz(String nameUz);

    boolean existsByNameEnOrNameUzOrNameRu(String nameEn, String nameUz, String nameRu);

    boolean existsById(Long id);

    @Query(value = "SELECT * FROM Region  WHERE id in :ids ORDER BY name_uz", nativeQuery = true)
    List<Region> getRegionByID(List<Long> ids);

    @Query(value = "SELECT region.transaction_count,region.name_uz FROM region group by transaction_count, region.name_uz order by name_uz",nativeQuery = true)
    List<List<Region>> findAllByGroupByTrxCount();

}
