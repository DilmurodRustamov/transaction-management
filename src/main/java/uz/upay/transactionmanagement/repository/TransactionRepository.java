package uz.upay.transactionmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.upay.transactionmanagement.entity.Region;
import uz.upay.transactionmanagement.entity.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

//    @Query(value = "SELECT count(transaction.region_id) FROM transaction WHERE transaction.region_id=?1", nativeQuery = true)
//    Integer trxCountPerProduct(Long productId);

    List<Transaction> findAllByOrderByProductId();

    @Query(value = "SELECT region_id FROM courier_regions WHERE courier_id=?1", nativeQuery = true)
    List<Long> getRegionIds(Long courierId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE transaction SET transaction_count = (SELECT MAX (transaction_count) from transaction)+1 where region_id=?1", nativeQuery = true)
    Object updateTrxCount(Long regionId);

    @Query(value = "SELECT COUNT (transaction.id),transaction.region_id, region.name_uz FROM" +
            " transaction JOIN region ON region.id=transaction.region_id GROUP BY transaction.region_id," +
            " region.name_uz ORDER BY region.name_uz", nativeQuery = true)
    List<Region> getRegions();

    @Query(value = "SELECT transaction.transaction_count FROM transaction group by transaction_count order by transaction_count desc ",nativeQuery = true)
    List<List<Region>> findAllByGroupByTrxCount();

    List<Transaction> findAllByOrderByTransactionCountDesc();

}
