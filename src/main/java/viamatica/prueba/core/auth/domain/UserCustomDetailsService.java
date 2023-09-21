package viamatica.prueba.core.auth.domain;


import java.util.List;
import viamatica.prueba.core.auth.UserCustomDetails;
import viamatica.prueba.module.user.UserRepository;
import viamatica.prueba.module.user.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import viamatica.prueba.module.role.RoleRepository;
import viamatica.prueba.module.role.domain.Role;

@Component
public class UserCustomDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired 
    private RoleRepository roleRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<User> userInfo = repository.findByUserNameOrMail(username, username);
        
        UserCustomDetails userDetails =  userInfo.map(UserCustomDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
        
        List<Role> roles = roleRepository
                .findByUsersIdUsuario(userInfo.get().getIdUsuario());
        
        userDetails.addAuthorities(roles);
        
        return userDetails;
    }
}
