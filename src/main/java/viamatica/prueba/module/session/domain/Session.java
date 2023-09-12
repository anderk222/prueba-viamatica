package viamatica.prueba.module.session.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import viamatica.prueba.module.user.domain.User;

@Data
@Entity
@Table(name = "sessions")
public class Session {
    
    @Id
    @Column(name = "user_id")
    private Long id;

    private LocalDate fechaIngreso;
    private LocalDate fechaCierre;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
    
}
