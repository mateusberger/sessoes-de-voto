package com.desafio.sessoesdevoto.infraestrutura.entidades;

import com.desafio.sessoesdevoto.dominio.Pauta;
import com.desafio.sessoesdevoto.dominio.Voto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Objeto para declaração do modelo de persistencia
 */
@Document("pautas")
public class PautaEntity {

    @Id
    private String id;

    private String nome;

    private String descricao;

    private LocalDateTime inicioDaSessao;

    private LocalDateTime terminoDaSessao;

    private List<Voto> votos = new ArrayList<>();

    public Pauta toPauta(){
        return new Pauta(
                this.getId(),
                this.getNome(),
                this.getDescricao(),
                this.getInicioDaSessao(),
                this.getTerminoDaSessao(),
                this.getVotos().toArray(Voto[]::new)
        );
    }

    public void atualizar(Pauta pauta){
        setNome(pauta.getNome());
        setDescricao(pauta.getDescricao());
        setInicioDaSessao(pauta.getInicioDaSessao());
        setTerminoDaSessao(pauta.getTerminoDaSessao());
    }

    public PautaEntity() {
    }

    public PautaEntity(String id) {
        this.id = id;
    }

    public PautaEntity(Pauta pauta){
        this.id = pauta.getId();
        this.nome = pauta.getNome();
        this.descricao = pauta.getDescricao();
        this.votos = pauta.getVotos();
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
        return votos == null ? new ArrayList<>() : votos;
    }

    public void setVotos(List<Voto> votos) {
        this.votos = votos;
    }
}
