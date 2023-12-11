package com.br.kanbi.quadro;

import com.br.kanbi.quadro.response.QuadroDTO;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/quadro")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON})
public class QuadroResource {
    @Inject
    @RestClient
    QuadroService quadroService;

    @POST
    @Path("/post")
    public Response insertQuadro(QuadroDTO quadroDTO){
        Response resClient = quadroService.insertQuadro(quadroDTO);
        return Response.ok(resClient.getEntity()).build();
    }

}
