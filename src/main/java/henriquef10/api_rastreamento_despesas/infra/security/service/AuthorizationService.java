package henriquef10.api_rastreamento_despesas.infra.security.service;

import henriquef10.api_rastreamento_despesas.core.entities.user.User;
import henriquef10.api_rastreamento_despesas.core.exception.user.UserNotFoundException;
import henriquef10.api_rastreamento_despesas.infra.security.model.CustomUserDetails;
import henriquef10.api_rastreamento_despesas.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByLogin(username).orElseThrow(() -> new UserNotFoundException("User not found!"));

        return new CustomUserDetails(user);
    }
}
