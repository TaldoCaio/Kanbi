package com.ads.kanvi.activity.user;

import com.ads.kanvi.activity.RetrofitClient;
import com.ads.kanvi.activity.card.response.CardApi;
import com.ads.kanvi.activity.user.response.UserApi;
import com.ads.kanvi.activity.user.response.UserDTO;

import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserService {

    private UserApi userApi;
    public UserService() {
        this.userApi = RetrofitClient.getRetrofitInstance().create(UserApi.class);
    }

    public UserService(UserApi userApi) {
        this.userApi = userApi;
    }

    public UserDTO createUser(UserDTO userDTO) throws IOException {
        Call<UserDTO> call = userApi.createUser(userDTO);
        Response<UserDTO> response = call.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new IOException("Error creating user");
        }
    }

    public List<UserDTO> getAllUsers() throws IOException {
        Call<List<UserDTO>> call = userApi.getAllUsers();
        Response<List<UserDTO>> response = call.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            // Tratar erros
            throw new IOException("Error getting all users");
        }
    }

    public Optional<UserDTO> findUserById(Integer id) throws IOException {
        Call<UserDTO> call = userApi.findUserById(id);
        Response<UserDTO> response = call.execute();

        if (response.isSuccessful()) {
            return Optional.ofNullable(response.body());
        } else {
            // Tratar erros
            throw new IOException("Error finding user by ID");
        }
    }

    public Response updateUser(UserDTO userDTO) throws IOException {
        Call<Response> call = userApi.updateUser(userDTO);
        Response<Response> response = call.execute();

        if (response.isSuccessful()) {
            return response;
        } else {
            // Tratar erros
            throw new IOException("Error updating user");
        }
    }

    public Response deleteUser(Integer id) throws IOException {
        Call<Response> call = userApi.deleteUser(id);
        Response<Response> response = call.execute();

        if (response.isSuccessful()) {
            return response;
        } else {
            // Tratar erros
            throw new IOException("Error deleting user");
        }
    }
}
