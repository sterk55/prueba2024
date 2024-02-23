package org.example.dto;


import lombok.Data;
import lombok.ToString;
import org.example.db.OrdenCompras;

@Data
@ToString
public class OrdenComprasDto {


    private Integer id;
    private String clienteNombre;
    private String productoNombre;
    private Integer precio;



    public static OrdenComprasDto from(OrdenCompras obj){
        OrdenComprasDto or = new OrdenComprasDto();

        or.setId(obj.getId());
        or.setPrecio(obj.getPrecio());

        return or;
    }

}
