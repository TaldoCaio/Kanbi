package com.br.kanbi.quadro.response;

public class QuadroDTO {
    private Integer id;
    private String descricao;
    private Integer criador;
    private Integer maxLista;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCriador() {
        return criador;
    }

    public void setCriador(Integer criador) {
        this.criador = criador;
    }

    public Integer getMaxLista() {
        return maxLista;
    }

    public void setMaxLista(Integer maxLista) {
        this.maxLista = maxLista;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
