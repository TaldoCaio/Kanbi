package com.br.kanbi.quadro;

import com.br.kanbi.quadro.response.QuadroDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class QuadroService {
    @Inject
    @RestClient
    QuadroRestClient quadroRestClient;

    QuadroDTO insertQuadro(QuadroDTO quadroDTO){
        return quadroRestClient.insertQuadro(quadroDTO);
    }
    List<QuadroDTO> getAll(){
        return quadroRestClient.getAll();
    }
    Optional<QuadroDTO> getById(Integer id){
        return quadroRestClient.getById(id);
    }
    List<QuadroDTO> getByCriador(Integer criador){
        return quadroRestClient.getByCriador(criador);
    }
    List<QuadroDTO> getAtivoByCriador(Integer criador) {
        return quadroRestClient.getAtivoByCriador(criador);
    }




}
