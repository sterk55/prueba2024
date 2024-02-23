package org.example.rest;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.clients.ClienteRestClient;
import org.example.clients.ProductoRestClient;
import org.example.db.OrdenCompras;
import org.example.dto.OrdenComprasDto;
import org.example.repo.OrdenComprasRepository;

import java.util.List;
import java.util.stream.Collectors;

@Path("/ordenes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class OrdenCompraRest {

    @Inject
    OrdenComprasRepository repo;

    @Inject
    @RestClient
    ClienteRestClient clienteClient;


    @Inject
    @RestClient
    ProductoRestClient productoClient;



    @GET
    public List<OrdenComprasDto> findAll() {
        return repo.streamAll()
                .map(ordenes->{
                    var cliente = clienteClient.findById(ordenes.getClienteId());

                    var dto = OrdenComprasDto.from(ordenes);

                    String aname = String.format("%s %s",
                            cliente.getNombre(), cliente.getApellido());

                    dto.setClienteNombre(aname); ;

                    return dto;
                })
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id")Integer id) {
        var ordenes = repo.findByIdOptional(id);

        if(ordenes.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(ordenes.get()).build();
    }

    @POST
    public Response create(OrdenCompras obj) {
        obj.setId(null);

        repo.persist(obj);

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")

    public Response update(@PathParam("id")Integer id, OrdenCompras obj) {

        OrdenCompras b = repo.findById(id);

        b.setPrecio(obj.getPrecio());
        b.setClienteId(obj.getClienteId());
        b.setProductoId(obj.getProductoId());

        return Response.ok()
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")Integer id) {
        repo.deleteById(id);

        return Response.ok()
                .build();
    }
}
