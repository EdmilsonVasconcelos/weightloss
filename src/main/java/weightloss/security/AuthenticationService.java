package weightloss.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import weightloss.domain.User;
import weightloss.respository.UserRepository;

@Slf4j
@Service
public class AuthenticationService implements UserDetailsService {

    private static final String INCORRECTS_INFORMATIONS_FOR_EMAIL = "Informações incorretas para e-mail %s.";

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.debug("AuthenticationService.loadUserByUsername - Start - Email:  [{}]", username);

        Optional<User> user = userRepository.findByEmail(username);

        if(user.isPresent()) {

            log.debug("AuthenticationService.loadUserByUsername - Finish - Email:  [{}]", username);

            return user.get();

        }

        log.debug("AuthenticationService.loadUserByUsername - Finish - Email:  [{}], Message [{}]", username, INCORRECTS_INFORMATIONS_FOR_EMAIL);

        throw new UsernameNotFoundException(INCORRECTS_INFORMATIONS_FOR_EMAIL);
    }

}

