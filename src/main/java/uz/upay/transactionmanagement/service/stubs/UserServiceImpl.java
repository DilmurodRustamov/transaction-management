package uz.upay.transactionmanagement.service.stubs;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.upay.transactionmanagement.entity.Region;
import uz.upay.transactionmanagement.entity.User;
import uz.upay.transactionmanagement.payload.AdminDto;
import uz.upay.transactionmanagement.payload.ApiResponse;
import uz.upay.transactionmanagement.payload.CourierDto;
import uz.upay.transactionmanagement.ref.UserRole;
import uz.upay.transactionmanagement.repository.RegionRepository;
import uz.upay.transactionmanagement.repository.UserRepository;
import uz.upay.transactionmanagement.service.UserService;

import javax.transaction.Transactional;

import java.util.*;

import static uz.upay.transactionmanagement.apiResponsdeMessages.ResponseMessageKeys.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RegionRepository regionRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public ApiResponse createAmin(AdminDto adminUserDto) {
        return createUser(adminUserDto.mapToUser(passwordEncoder));
    }

    @Override
    @Transactional
    public ApiResponse createCourier(CourierDto courierUserDto) {
        if (userRepository.existsByPhoneNumber(courierUserDto.getPhoneNumber()))
            return new ApiResponse(COURIER_ALREADY_EXISTS, false);
        User user = new User();
        Set<Region> regions = new HashSet<>();
        for (Region region : courierUserDto.getRegions()) {
            Optional<Region> optionalRegion = regionRepository.findByNameUz(region.getNameUz());
            optionalRegion.ifPresent(regions::add);
            user.setName(courierUserDto.getName());
            user.setPhoneNumber(courierUserDto.getPhoneNumber());
            user.setRegions(regions);
            user.setPassword(passwordEncoder.encode(courierUserDto.getPassword()));
            user.setUserRole(UserRole.COURIER);
            userRepository.save(user);
            return new ApiResponse(COURIER_SAVED, false);
        }


        return createUser(courierUserDto.mapToUser(passwordEncoder));
    }

    private ApiResponse createUser(User user) {
        if (userRepository.existsByPhoneNumber(user.getPhoneNumber()))
            return new ApiResponse(USER_ALREADY_EXISTS, false);
        userRepository.save(user);
        return new ApiResponse(USER_SAVED, false);
    }
}
