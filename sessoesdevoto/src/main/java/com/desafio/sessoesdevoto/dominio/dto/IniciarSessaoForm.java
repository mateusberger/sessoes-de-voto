package com.desafio.sessoesdevoto.dominio.dto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Formulário com dados de início e termino de uma sessão de votação em uma pauta
 *
 * @param idPauta ID da pauta (Obrigatório)
 * @param inicioDaSessao Momento que é iniciada a Sessão (Se nulo, será utilizado a momento atual)
 * @param terminoDaSessao Momento que é finalizada a Sessão (Se nulo, será utilizado a momento atual + 1 minuto)
 */
public record IniciarSessaoForm(
        String idPauta,
        LocalDateTime inicioDaSessao,
        LocalDateTime terminoDaSessao
) {

    public static Clock clock = Clock.systemDefaultZone();

    public IniciarSessaoForm {

        LocalDateTime agora = LocalDateTime.now(clock);

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