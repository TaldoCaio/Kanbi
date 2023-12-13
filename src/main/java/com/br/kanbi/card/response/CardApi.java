package com.br.kanbi.card.response;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CardApi {

    @POST("/card/")
    Call<CardDTO> inserirCard(@Body CardDTO cardDTO);

    @GET("/card/all")
    Call<List<CardDTO>> getAll();

    @GET("/card/{id}")
    Call<Optional<CardDTO>> getById(@Path("id") Integer id);

    @GET("/card/criador/{criador}")
    Call<List<CardDTO>> getByCriador(@Path("criador") Integer criador);

    @GET("/card/lst/{lista}")
    Call<List<CardDTO>> getByLista(@Path("lista") Integer lista);

    @GET("/card/attbyCriador/{criador}")
    Call<List<CardDTO>> getAtt(@Path("criador") Integer criador);

    @GET("/card/dtIni/{dtInicio}")
    Call<List<CardDTO>> getByDtIni(@Path("dtInicio") Date dtInicio);

    @GET("/card/dtTer/{dtTermino}")
    Call<List<CardDTO>> getByDtTermino(@Path("dtTermino") Date dtTermino);
}

