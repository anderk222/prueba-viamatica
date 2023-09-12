package viamatica.prueba.module.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import viamatica.prueba.module.session.domain.Session;
import viamatica.prueba.module.role.domain.Role;


@Data
@Entity
@Table(name = "usuarios")
@SQLDelete(sql = "UPDATE usuarios SET deleted = true WHERE id_usuario=?")
@Where(clause = "deleted=false")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idUsuario;

    @Column(nullable = false)
  
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]*$", 
            message = "incorrect username your username should sees like Anderk222")
    @Length(min = 8, max = 20)
    private String userName;
    
    @Column(nullable=false)
    @Email
    private String mail;
    
    @Lob
    @Column(columnDefinition = "text")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private String password;

    private boolean sessionActive = Boolean.FALSE;

    private int logueosIncorrectos = 0;

    private boolean deleted = Boolean.FALSE;

    
       @ManyToMany()
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )

    @JsonIgnore
    private Set<Role> roles;
    

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Session session;
    
}