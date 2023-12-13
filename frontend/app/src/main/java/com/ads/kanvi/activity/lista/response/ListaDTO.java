package com.ads.kanvi.activity.lista.response;

import java.util.Date;

public class ListaDTO {
    private Integer id;
    private String nome;
    private Integer criador;
    private String status;
    private Date dataCriacao;
    private Integer quadro;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCriador() {
        return criador;
    }

    public void setCriador(Integer criador) {
        this.criador = criador;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Integer getQuadro() {
        return quadro;
    }

    public void setQuadro(Integer quadro) {
        this.quadro = quadro;
    }
}
