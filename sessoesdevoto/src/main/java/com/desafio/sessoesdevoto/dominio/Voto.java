package com.desafio.sessoesdevoto.dominio;

public class Voto {

    private String cpfDoAssociado;

    private Boolean voto;

    private Integer idDaPauta;

    public Voto() {
    }

    public Voto(String cpfDoAssociado, Boolean voto, Integer idDaPauta) {
        this.cpfDoAssociado = cpfDoAssociado;
        this.voto = voto;
        this.idDaPauta = idDaPauta;
    }

    public String getCpfDoAssociado() {
        return cpfDoAssociado;
    }

    public void setCpfDoAssociado(String cpfDoAssociado) {
        this.cpfDoAssociado = cpfDoAssociado;
    }

    public Boolean getVoto() {
        return voto;
    }

    public void setVoto(Boolean voto) {
        this.voto = voto;
    }

    public Integer getIdDaPauta() {
        return idDaPauta;
    }

    public void setIdDaPauta(Integer idDaPauta) {
        this.idDaPauta = idDaPauta;
    }
}
