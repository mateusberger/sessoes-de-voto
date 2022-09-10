package com.desafio.sessoesdevoto.dominio.dto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public record IniciarSessaoForm(
        String idPauta,
        LocalDateTime inicioDaSessao,
        LocalDateTime terminoDaSessao
) {

    public IniciarSessaoForm {

        LocalDateTime agora = LocalDateTime.now();

        if (idPauta == null) throw new IllegalArgumentException("Id da pauta não foi informado");

        if (idPauta.isBlank()) throw new IllegalArgumentException("Id da pauta não pode ser vazio");

        if (inicioDaSessao == null) inicioDaSessao = agora;

        if (inicioDaSessao.isBefore(agora))
            throw new IllegalArgumentException("Inicio da sessão não pode ser antes do presente momento");

        if (terminoDaSessao == null) terminoDaSessao = agora.plus(1, ChronoUnit.MINUTES);

        if (terminoDaSessao.isBefore(inicioDaSessao))
            throw new IllegalArgumentException("Termino da sessão não pode ocorrer antes do inicio");
    }
}