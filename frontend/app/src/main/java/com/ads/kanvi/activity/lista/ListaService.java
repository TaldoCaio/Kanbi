package com.ads.kanvi.activity.lista;

import com.ads.kanvi.activity.RetrofitClient;
import com.ads.kanvi.activity.card.response.CardApi;
import com.ads.kanvi.activity.lista.response.ListaApi;
import com.ads.kanvi.activity.lista.response.ListaDTO;

import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ListaService {

    private ListaApi listaApi;
    public ListaService() {
        this.listaApi = RetrofitClient.getRetrofitInstance().create(ListaApi.class);
    }

    public ListaService(ListaApi listaApi) {
        this.listaApi = listaApi;
    }

    public Response<ListaDTO> inserirLista(ListaDTO listaDTO) throws IOException {
        Call<ListaDTO> call = listaApi.inserirLista(listaDTO);
        Response<ListaDTO> response = call.execute();

        if (response.isSuccessful()) {
            return response; // Retorna a própria resposta Retrofit em caso de sucesso
        } else {
            // Tratar erros
            throw new IOException("Error creating list");
        }
    }

    public List<ListaDTO> getAll() throws IOException {
        Call<List<ListaDTO>> call = listaApi.getAll();
        Response<List<ListaDTO>> response = call.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            // Tratar erros
            throw new IOException("Error getting all lists");
        }
    }

    public Optional<ListaDTO> getById(Integer id) throws IOException {
        Call<Optional<ListaDTO>> call = listaApi.getById(id);
        Response<Optional<ListaDTO>> response = call.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            // Tratar erros
            throw new IOException("Error finding list by ID");
        }
    }

    public List<ListaDTO> getByCriador(Integer criador) throws IOException {
        Call<List<ListaDTO>> call = listaApi.getByCriador(criador);
        Response<List<ListaDTO>> response = call.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            // Tratar erros
            throw new IOException("Error getting lists by criador");
        }
    }

    public List<ListaDTO> getByQuadro(Integer quadro) throws IOException {
        Call<List<ListaDTO>> call = listaApi.getByQuadro(quadro);
        Response<List<ListaDTO>> response = call.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            // Tratar erros
            throw new IOException("Error getting lists by quadro");
        }
    }

    public ListaDTO updateLista(ListaDTO listaDTO) throws IOException {
        Call<ListaDTO> call = listaApi.updateLista(listaDTO);
        Response<ListaDTO> response = call.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            // Tratar erros
            throw new IOException("Error updating list");
        }
    }
}
