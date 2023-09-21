package viamatica.prueba.core.auth;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import viamatica.prueba.module.role.domain.Role;
import viamatica.prueba.module.roleoption.domain.RolOptions;

import viamatica.prueba.module.user.domain.User;

public class UserCustomDetails implements UserDetails {
    
    private String name;
    private String password;
    private List<GrantedAuthority> authorities = new ArrayList<>();

    public UserCustomDetails(User user) {
        name=user.getUserName();
        password=user.getPassword();
 
    
    }

    public void addAuthorities(List<Role> roles){
        
        for(Role role : roles){
             
            authorities.add(new SimpleGrantedAuthority(role.getRolName()));
            for(RolOptions rolopt : role.getRolOptions()){
                
                authorities
                        .add(new SimpleGrantedAuthority(rolopt.getNombreOpcion()));
            }
        }
        
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
