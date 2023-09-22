package viamatica.prueba.module.roleoption.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import viamatica.prueba.module.role.domain.Role;

@Data
@Entity
@Table(name = "rol_opciones")
public class RolOptions {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idOpcion;
    
    
    @Column(length = 50, nullable = false)
    private String nombreOpcion;

    @ManyToMany(mappedBy = "rolOptions", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonProperty(access = Access.WRITE_ONLY)
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role){

        roles.add(role);

    }

}