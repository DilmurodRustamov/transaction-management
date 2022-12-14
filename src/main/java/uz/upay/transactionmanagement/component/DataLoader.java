package uz.upay.transactionmanagement.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.upay.transactionmanagement.entity.User;
import uz.upay.transactionmanagement.ref.UserRole;
import uz.upay.transactionmanagement.repository.RoleRepository;
import uz.upay.transactionmanagement.repository.UserRepository;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String initMode;

    @Override
    public void run(String... args) throws Exception {
        if (Objects.equals(initMode, "always")) {
            User superAdmin = new User();
            superAdmin.setName("Super Admin");
            superAdmin.setPhoneNumber("998912345678");
            superAdmin.setUserRole(UserRole.SUPER_ADMIN);
            superAdmin.setPassword(passwordEncoder.encode("123456"));
            userRepository.save(superAdmin);

            User admin = new User();
            admin.setName("Admin");
            admin.setPhoneNumber("998912345679");
            admin.setUserRole(UserRole.ADMIN);
            admin.setPassword(passwordEncoder.encode("123456"));
            userRepository.save(admin);

        }
    }
}
