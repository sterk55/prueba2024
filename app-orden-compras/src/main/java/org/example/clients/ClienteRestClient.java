package org.example.clients;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.dto.ClienteDto;

import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@RegisterRestClient(baseUri = "http://localhost:9090")
//@RegisterRestClient
@RegisterRestClient(configKey = "ClienteRestClient")
public interface ClienteRestClient {


    @GET
    public List<ClienteDto> findAll();

    @GET
    @Path("/{id}")
    public ClienteDto findById(@PathParam("id") Integer id);
}
