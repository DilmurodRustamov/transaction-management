package uz.upay.transactionmanagement.service.stubs;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.upay.transactionmanagement.entity.User;
import uz.upay.transactionmanagement.payload.ApiResponse;
import uz.upay.transactionmanagement.payload.LoginDto;
import uz.upay.transactionmanagement.payload.RegisterDto;
import uz.upay.transactionmanagement.ref.UserRole;
import uz.upay.transactionmanagement.repository.RoleRepository;
import uz.upay.transactionmanagement.repository.UserRepository;
import uz.upay.transactionmanagement.security.JwtProvider;
import uz.upay.transactionmanagement.security.SecurityUser;
import uz.upay.transactionmanagement.service.AuthService;

import static uz.upay.transactionmanagement.apiResponsdeMessages.ResponseMessageKeys.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Override
    public ApiResponse registerUser(RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getPerPassword()))
            return new ApiResponse(PASSWORD_NOT_EQUALS, false);
        boolean existsByPhoneNumber = userRepository.existsByPhoneNumber(registerDto.getPhoneNumber());
        if (existsByPhoneNumber)
            return new ApiResponse(USER_ALREADY_EXISTS, false);
        User user = new User(
                registerDto.getName(),
                registerDto.getPhoneNumber(),
                passwordEncoder.encode(registerDto.getPassword()),
                UserRole.USER
        );
        userRepository.save(user);
        return new ApiResponse(USER_SAVED, true);
    }

    @Override
    public String loginUser(LoginDto loginDto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getPhoneNumber(), loginDto.getPassword()));
        SecurityUser user = (SecurityUser) authenticate.getPrincipal();
        return JwtProvider.generateToken(user.getPhoneNumber(), user.getUserRole());
    }
}
