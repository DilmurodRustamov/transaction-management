package uz.upay.transactionmanagement.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import uz.upay.transactionmanagement.repository.UserRepository;


@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return new SecurityUser(userRepository.findByPhoneNumber(username).orElseThrow(()->new UsernameNotFoundException(username)));
    }

}
