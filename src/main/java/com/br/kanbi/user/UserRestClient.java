package com.br.kanbi.user;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import com.br.kanbi.user.response.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@RegisterRestClient
@Path("/user")
w
public interface UserRestClient {

    @POST
    @Path("/criar")
    UserDTO createUser(UserDTO userDTO);

    @GET
    @Path("/all")
    List<UserDTO> getAllUsers();

    @GET
    @Path("/{id}")
    Optional<UserDTO> findUserById(Integer id);

    @PUT
    @Path("/updateU")
    Response updateUser(UserDTO userDTO);

    @DELETE
    @Path("/delete/{id}")
    Response deleteUser(Integer id);
}
