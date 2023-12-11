package com.br.kanbi.quadro;

import com.br.kanbi.quadro.response.QuadroDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.PartType;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@RegisterRestClient
@Path("/quadro")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public interface QuadroRestClient {

    @POST
    @Path("/post")
    QuadroDTO insertQuadro(QuadroDTO quadroDTO);

    @GET
    @Path("/all")
    List<QuadroDTO> getAll();

    @GET
    @Path("/{id}")
    Optional<QuadroDTO> getById(Integer id);

    @GET
    @Path("/criador/{criador}")
    List<QuadroDTO> getByCriador(Integer criador);

    @GET
    @Path("/ativosCriador/{criador}")
    List<QuadroDTO> getAtivoByCriador(Integer criador);
}
