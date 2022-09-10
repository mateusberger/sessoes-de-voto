package com.desafio.sessoesdevoto.dominio;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pauta {

    private Integer id;

    private String nome;

    private String descricao;

    private LocalDateTime inicioDaSessao;

    private LocalDateTime terminoDaSessao;


    private List<Voto> votos;

    public Pauta() {
    }

    public Pauta(Integer id, String nome, String descricao, LocalDateTime inicioDaSessao, LocalDateTime terminoDaSessao, Voto... votos) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.inicioDaSessao = inicioDaSessao;
        this.terminoDaSessao = terminoDaSessao;
        this.votos = votos == null ? new ArrayList<Voto>() : Arrays.asList(votos);
    }

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getInicioDaSessao() {
        return inicioDaSessao;
    }

    public void setInicioDaSessao(LocalDateTime inicioDaSessao) {
        this.inicioDaSessao = inicioDaSessao;
    }

    public LocalDateTime getTerminoDaSessao() {
        return terminoDaSessao;
    }

    public void setTerminoDaSessao(LocalDateTime terminoDaSessao) {
        this.terminoDaSessao = terminoDaSessao;
    }

    public List<Voto> getVotos() {
        return votos;
    }

    public void setVotos(List<Voto> votos) {
        this.votos = votos;
    }
}
