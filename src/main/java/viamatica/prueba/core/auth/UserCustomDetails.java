package viamatica.prueba.core.auth;

import java.util.Collection;
import java.util.stream.Collectors;

import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import viamatica.prueba.module.user.domain.User;

public class UserCustomDetails implements UserDetails {
    
    private String name;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserCustomDetails(User user) {
        name=user.getUserName();
        password=user.getPassword();
        authorities = user.getRoles().stream()
        .map((role)-> new SimpleGrantedAuthority(role.getRolName()))
        .collect(Collectors.toList());
    
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
