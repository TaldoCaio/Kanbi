package com.ads.kanvi.activity.user.response;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.*;

import java.util.List;

public interface UserApi {

    @POST("/user/criar")
    Call<UserDTO> createUser(@Body UserDTO userDTO);

    @GET("/user/all")
    Call<List<UserDTO>> getAllUsers();

    @GET("/user/{id}")
    Call<UserDTO> findUserById(@Path("id") Integer id);

    @PUT("/user/updateU")
    Call<Response> updateUser(@Body UserDTO userDTO);

    @DELETE("/user/delete/{id}")
    Call<Response> deleteUser(@Path("id") Integer id);
}

