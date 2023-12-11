package com.br.kanbi.user;

import com.br.kanbi.user.UserRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import com.br.kanbi.user.response.UserDTO;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserService {
    @Inject
    @RestClient
    UserRestClient userRestClient;

    UserDTO createUser (UserDTO userDTO){
        return userRestClient.createUser(userDTO);
    }
    List<UserDTO> getAllUsers(){
        return userRestClient.getAllUsers();
    }
    Optional<UserDTO> findUsersById(Integer id){
        return userRestClient.findUserById(id);
    }
    Response updateUser(UserDTO userDTO){return userRestClient.updateUser(userDTO);}
    Response deleteUser(Integer id){
        return userRestClient.deleteUser(id);
    }
}
