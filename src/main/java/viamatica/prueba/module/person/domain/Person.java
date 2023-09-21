package viamatica.prueba.module.person.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import viamatica.prueba.module.user.domain.User;

@Data
@Entity
@Table(name = "persona")
@SQLDelete(sql = "UPDATE persona SET deleted = true WHERE id_persona=?")
@Where(clause = "deleted=false")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idPersona;

    @Column(nullable = false, length = 255)
    private String nombres;

    @Column(nullable = false, length = 255)
    private String apellidos;

    @Column(nullable = false, length = 10, unique = true)
    @Length(min = 10, max = 10)
    @Pattern(regexp = "^(?!.*(\\d)\\1{3})\\d+$")
    private String identificacion;

    @Column(columnDefinition = "DATE")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @OneToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.MERGE, CascadeType.PERSIST })
    private User user;

    @JsonIgnore
    private boolean deleted = Boolean.FALSE;
    
    
    public String getFullName() {

        return this.nombres + " " + this.getApellidos();

    }

}
