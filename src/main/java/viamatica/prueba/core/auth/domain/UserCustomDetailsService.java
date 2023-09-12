package viamatica.prueba.core.auth.domain;


import viamatica.prueba.core.auth.UserCustomDetails;
import viamatica.prueba.module.user.UserRepository;
import viamatica.prueba.module.user.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserCustomDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInfo = repository.findByUserNameOrMail(username, username);
        return userInfo.map(UserCustomDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
}
