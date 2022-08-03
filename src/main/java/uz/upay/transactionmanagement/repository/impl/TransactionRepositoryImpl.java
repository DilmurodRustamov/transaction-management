//package uz.upay.transactionmanagement.repository.impl;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//import uz.upay.transactionmanagement.entity.Region;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//import java.util.List;
//
//@Repository
//@RequiredArgsConstructor
//public class TransactionRepositoryImpl {
//    private final EntityManager entityManager;
//    public List<Region> list(){
//        String jpql = "select r.id, r.name, count(t.id) from region r join trasaction t on t.region_id = r.id group by r.id, r.name";
//        Query query = entityManager.createNativeQuery(jpql,);
//        query.executeUpdate();
//    }
//}
