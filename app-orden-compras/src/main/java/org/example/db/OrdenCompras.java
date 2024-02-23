package org.example.db;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="ordenCompras")
@Data
public class OrdenCompras {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="cliente_id")
    private Integer clienteId;

    @Column(name="producto_id")
    private Integer productoId;

    private Integer precio;



}
