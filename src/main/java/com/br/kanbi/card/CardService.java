package com.br.kanbi.card;

import com.br.kanbi.RetrofitClient;
import com.br.kanbi.card.response.CardApi;
import com.br.kanbi.card.response.CardDTO;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CardService {

    private final CardApi cardApi;

    public CardService() {
        this.cardApi = RetrofitClient.getRetrofitInstance().create(CardApi.class);
    }

    public Response<CardDTO> inserirCard(CardDTO cardDTO) throws IOException {
        Call<CardDTO> call = cardApi.inserirCard(cardDTO);
        Response<CardDTO> response = call.execute();

        if (response.isSuccessful()) {
            return response;
        } else {
            // Tratar erros
            throw new IOException("Error creating card");
        }
    }

    public List<CardDTO> getAll() throws IOException {
        Call<List<CardDTO>> call = cardApi.getAll();
        Response<List<CardDTO>> response = call.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            // Tratar erros
            throw new IOException("Error getting all cards");
        }
    }

    public Optional<CardDTO> getById(Integer id) throws IOException {
        Call<Optional<CardDTO>> call = cardApi.getById(id);
        Response<Optional<CardDTO>> response = call.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            // Tratar erros
            throw new IOException("Error getting card by ID");
        }
    }

    public List<CardDTO> getByCriador(Integer criador) throws IOException {
        Call<List<CardDTO>> call = cardApi.getByCriador(criador);
        Response<List<CardDTO>> response = call.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            // Tratar erros
            throw new IOException("Error getting cards by criador");
        }
    }

    public List<CardDTO> getByLista(Integer lista) throws IOException {
        Call<List<CardDTO>> call = cardApi.getByLista(lista);
        Response<List<CardDTO>> response = call.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            // Tratar erros
            throw new IOException("Error getting cards by lista");
        }
    }

    public List<CardDTO> getAtt(Integer criador) throws IOException {
        Call<List<CardDTO>> call = cardApi.getAtt(criador);
        Response<List<CardDTO>> response = call.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            // Tratar erros
            throw new IOException("Error getting cards by criador for update");
        }
    }

    public List<CardDTO> getByDtIni(Date dtInicio) throws IOException {
        Call<List<CardDTO>> call = cardApi.getByDtIni(dtInicio);
        Response<List<CardDTO>> response = call.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            // Tratar erros
            throw new IOException("Error getting cards by start date");
        }
    }

    public List<CardDTO> getByDtTermino(Date dtTermino) throws IOException {
        Call<List<CardDTO>> call = cardApi.getByDtTermino(dtTermino);
        Response<List<CardDTO>> response = call.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            // Tratar erros
            throw new IOException("Error getting cards by end date");
        }
    }
}
