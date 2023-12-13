package com.ads.kanvi.activity.quadro.response;

public class QuadroDTO {
    private Integer Quadro_id;
    private String descricao;
    private Integer criador;
    private Integer maxLista;
    private String status;

    public Integer getId() {
        return Quadro_id;
    }

    public void setId(Integer id) {
        this.Quadro_id = id;
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

    @Override
    public String toString() {
        return "QuadroDTO{" +
                "id=" + Quadro_id +
                ", descricao='" + descricao + '\'' +
                ", criador=" + criador +
                ", maxLista=" + maxLista +
                ", status='" + status + '\'' +
                '}';
    }
}
