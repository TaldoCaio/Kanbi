package com.br.kanbi.quadro.response;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Optional;

public interface QuadroApi {

    @POST("/quadro/post")
    Call<QuadroDTO> insertQuadro(@Body QuadroDTO quadroDTO);

    @GET("/quadro/all")
    Call<List<QuadroDTO>> getAll();

    @GET("/quadro/{id}")
    Call<Optional<QuadroDTO>> getById(@Path("id") Integer id);

    @GET("/quadro/criador/{criador}")
    Call<List<QuadroDTO>> getByCriador(@Path("criador") Integer criador);

    @GET("/quadro/ativos/{criador}")
    Call<List<QuadroDTO>> getAtivoByCriador(@Path("criador") Integer criador);
}
