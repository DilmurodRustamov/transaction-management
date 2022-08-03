package uz.upay.transactionmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.upay.transactionmanagement.entity.District;

import javax.transaction.Transactional;

public interface DistrictRepository extends JpaRepository<District,Long> {

    @Transactional
    void deleteByRegionId(Long regionId);

    boolean existsByNameEnOrNameUzOrNameRu(String nameEn, String nameUz, String nameRu);

    boolean existsByNameUz(String nameUz);
}
