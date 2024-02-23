package org.example.dto;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductoDto {

    private Integer id;
    private String nombre;
    private Integer precio;

}
