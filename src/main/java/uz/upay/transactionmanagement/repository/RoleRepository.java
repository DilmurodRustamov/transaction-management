package uz.upay.transactionmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.upay.transactionmanagement.entity.Role;
import uz.upay.transactionmanagement.ref.UserRole;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByRoleName(UserRole roleName);

}
