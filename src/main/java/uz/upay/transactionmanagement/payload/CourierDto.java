package uz.upay.transactionmanagement.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.upay.transactionmanagement.entity.Region;
import uz.upay.transactionmanagement.entity.User;
import uz.upay.transactionmanagement.ref.UserRole;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourierDto {

    @NotNull
    private String name;

    @NotNull
    @Pattern(regexp = "^998(9[012345789]|6[125679]|7[01234569])[0-9]{7}$", message = "user.phone.invalid")
    private String phoneNumber;

    @NotNull
    private String password;

    @NotNull
    private String perPassword;

    @NotBlank
    private Set<Region> regions;

    public User mapToUser(PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setName(this.name);
        user.setPhoneNumber(this.phoneNumber);
        user.setRegions(this.regions);
        user.setPassword(passwordEncoder.encode(this.password));
        user.setUserRole(UserRole.COURIER);
        return user;
    }

}
