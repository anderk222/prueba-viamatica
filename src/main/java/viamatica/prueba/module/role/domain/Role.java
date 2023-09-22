package viamatica.prueba.module.role.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import viamatica.prueba.module.roleoption.domain.RolOptions;
import viamatica.prueba.module.user.domain.User;

@Data
@Entity
@Table(name = "rol")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRol;

    @Column(length = 50, nullable = false)
    private String rolName;

    @ManyToMany()
    @JoinTable(name = "role_role_options",
            joinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "idRol"),
            inverseJoinColumns = @JoinColumn(name = "rol_options_id", referencedColumnName = "idOpcion")
    )
    private Set<RolOptions> rolOptions = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "idRol"),
            inverseJoinColumns = @JoinColumn(name = "id_user", referencedColumnName = "idUsuario"))
    Set<User> users = new HashSet<>();

    public void addUser(User user) {

        users.add(user);

    }

}
