package viamatica.prueba.module.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import viamatica.prueba.exception.AuthAttempsExceededException;
import viamatica.prueba.module.role.domain.Role;

@Data
@Entity
@Table(name = "usuarios")
@SQLDelete(sql = "UPDATE usuarios SET deleted = true WHERE id_usuario=?")
@Where(clause = "deleted=false")  
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false,unique = true)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]*$", message = "incorrect username your username should sees like Anderk222")
    @Length(min = 8, max = 20)
    private String userName;

    @Column(nullable = false, unique = true)
    @Email
    private String mail;

    @Column(columnDefinition = "text", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 8)
    private String password;

    private boolean sessionActive = Boolean.FALSE;

    private int logueosIncorrectos = 0;

    @JsonIgnore
    private boolean deleted = Boolean.FALSE;

    @ManyToMany(mappedBy = "users")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Role> roles = new HashSet<>();

    public void sumlogueosIncorrectos() {

        logueosIncorrectos++;

    }

        public void verifyFailedAuth() {

        if (this.logueosIncorrectos > 3)
            throw new AuthAttempsExceededException();

    }


}
