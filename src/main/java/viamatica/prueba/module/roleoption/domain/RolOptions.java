package viamatica.prueba.module.roleoption.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "rol_opciones")
public class RolOptions {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_opcion")
    private long id;
    
    
    @Column(length = 50, nullable = false)
    private String nombreOpcion;
    
}