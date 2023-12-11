package com.br.kanbi.user;

import com.br.kanbi.user.response.UserDTO;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON})
public class UserResource {
    @Inject
    @RestClient
    UserService userService;

    @POST
    @Path("/criar")
    public UserDTO createUser(UserDTO userDTO){
        return userService.createUser(userDTO);
    }
    @GET
    @Path("/all")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }
    @GET
    @Path("/{id}")
    public Optional<UserDTO> findUserById(Integer id){
        return userService.findUsersById(id);
    }
    @PUT
    @Path("/updateU")
    public Response updateUser(UserDTO userDTO){
        return userService.updateUser(userDTO);
    }
    @DELETE
    @Path("/delete/{id}")
    public Response deleteUser(@PathParam("id") Integer id){
        return userService.deleteUser(id);
    }

}
