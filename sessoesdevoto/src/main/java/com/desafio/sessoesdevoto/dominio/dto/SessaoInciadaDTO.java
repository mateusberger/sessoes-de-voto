package com.desafio.sessoesdevoto.dominio.dto;

import com.desafio.sessoesdevoto.dominio.Pauta;

import java.time.LocalDateTime;

public record SessaoInciadaDTO(
        String idPauta,
        LocalDateTime inicioDaSessao,
        LocalDateTime terminoDaSessao
) {
    public static SessaoInciadaDTO pautaToSessaoIniciadaDTO(Pauta pauta) {
        return new SessaoInciadaDTO(
                pauta.getId(),
                pauta.getInicioDaSessao(),
                pauta.getTerminoDaSessao()
        );
    }
}
