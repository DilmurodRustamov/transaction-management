package uz.upay.transactionmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.upay.transactionmanagement.entity.Region;
import uz.upay.transactionmanagement.entity.Request;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request,Long> {

}
