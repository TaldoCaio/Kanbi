package com.br.kanbi.quadro;

import com.br.kanbi.quadro.response.QuadroApi;
import com.br.kanbi.quadro.response.QuadroDTO;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class QuadroService {

    private final QuadroApi quadroApi;

    public QuadroService(QuadroApi quadroApi) {
        this.quadroApi = quadroApi;
    }

    public QuadroDTO insertQuadro(QuadroDTO quadroDTO) throws IOException {
        Call<QuadroDTO> call = quadroApi.insertQuadro(quadroDTO);
        Response<QuadroDTO> response = call.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            // Tratar erros
            throw new IOException("Error creating quadro");
        }
    }

    public List<QuadroDTO> getAll() throws IOException {
        Call<List<QuadroDTO>> call = quadroApi.getAll();
        Response<List<QuadroDTO>> response = call.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            // Tratar erros
            throw new IOException("Error getting all quadros");
        }
    }

    public Optional<QuadroDTO> getById(Integer id) throws IOException {
        Call<Optional<QuadroDTO>> call = quadroApi.getById(id);
        Response<Optional<QuadroDTO>> response = call.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            // Tratar erros
            throw new IOException("Error finding quadro by ID");
        }
    }

    public List<QuadroDTO> getByCriador(Integer criador) throws IOException {
        Call<List<QuadroDTO>> call = quadroApi.getByCriador(criador);
        Response<List<QuadroDTO>> response = call.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            // Tratar erros
            throw new IOException("Error getting quadros by criador");
        }
    }

    public List<QuadroDTO> getAtivoByCriador(Integer criador) throws IOException {
        Call<List<QuadroDTO>> call = quadroApi.getAtivoByCriador(criador);
        Response<List<QuadroDTO>> response = call.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            // Tratar erros
            throw new IOException("Error getting active quadros by criador");
        }
    }
}
