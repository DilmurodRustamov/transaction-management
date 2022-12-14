package uz.upay.transactionmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.upay.transactionmanagement.ref.UserRole;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @Column(nullable = false)
    @OrderBy(value = "nameUz asc")
    @JoinTable(
            name = "courier_regions",
            joinColumns = @JoinColumn(name = "courier_id"),
            inverseJoinColumns = @JoinColumn(name = "region_id"))
    private Set<Region> regions= new HashSet<>();

    public User(String name, String phoneNumber, String password, UserRole userRole) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userRole = userRole;
    }

    public static User initializeUserWithUserRole(String phoneNumber) {
        User user = new User();
        user.setPhoneNumber(phoneNumber);
        user.setUserRole(UserRole.USER);
        return user;
    }
}

