package viamatica.prueba.module.role.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;



import lombok.Data;
import viamatica.prueba.module.roleoption.domain.RolOptions;

@Data
@Entity
@Table(name="rol")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idRol;
    
    @Column(length = 50, nullable = false)
    private String rolName;
    
    @ManyToMany(cascade = { 
        CascadeType.MERGE,CascadeType.PERSIST
    })
    @JoinTable(name = "role_options",
            joinColumns = @JoinColumn(name = "rol_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_optiones_id")
    )
    
    private Set<RolOptions> rolOptions;

}