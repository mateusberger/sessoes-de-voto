package com.desafio.sessoesdevoto.dominio;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe que representa uma pauta de votação, responsável por gerir uma sessão de votação e contem os votos
 */
public class Pauta {

    private String id;

    private String nome;

    private String descricao;

    private LocalDateTime inicioDaSessao;

    private LocalDateTime terminoDaSessao;

    private List<Voto> votos;

    public Pauta() {
    }

    public Pauta(String id, String nome, String descricao, LocalDateTime inicioDaSessao, LocalDateTime terminoDaSessao, Voto... votos) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.inicioDaSessao = inicioDaSessao;
        this.terminoDaSessao = terminoDaSessao;
        this.votos = votos == null ? new ArrayList<>() : Arrays.asList(votos);
    }

    public boolean isSessaoAgendada(){
        return getInicioDaSessao() != null;
    }

    public boolean isSessaoIniciada(){
        return getInicioDaSessao() != null && LocalDateTime.now().isAfter(getInicioDaSessao());
    }

    public boolean isSessaoTerminada(){
        return getTerminoDaSessao() != null && LocalDateTime.now().isAfter(getTerminoDaSessao());
    }

    public long getTotalDeVotos(){
        return votos == null ? 0 : votos.size();
    }

    public long getTotalDeVotosSim(){
        return votos == null ? 0 : votos.stream().filter(Voto::getVoto).count();
    }

    public long getTotalDeVotosNao(){
        return  votos == null ? 0 : votos.stream().filter(v ->!v.getVoto()).count();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
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
