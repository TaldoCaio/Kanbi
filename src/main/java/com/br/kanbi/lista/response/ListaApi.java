package com.br.kanbi.lista.response;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Optional;

public interface ListaApi {

    @POST("/lista/criar")
    Call<ListaDTO> inserirLista(@Body ListaDTO listaDTO);

    @GET("/lista/todos")
    Call<List<ListaDTO>> getAll();

    @GET("/lista/id/{id}")
    Call<Optional<ListaDTO>> getById(@Path("id") Integer id);

    @GET("/lista/criador/{criador}")
    Call<List<ListaDTO>> getByCriador(@Path("criador") Integer criador);

    @GET("/lista/quadro/{quadro}")
    Call<List<ListaDTO>> getByQuadro(@Path("quadro") Integer quadro);

    @PATCH("/lista/update")
    Call<ListaDTO> updateLista(@Body ListaDTO listaDTO);
}
