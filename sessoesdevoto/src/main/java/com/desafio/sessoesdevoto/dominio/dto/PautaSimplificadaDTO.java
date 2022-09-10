package com.desafio.sessoesdevoto.dominio.dto;

import com.desafio.sessoesdevoto.dominio.Pauta;

public record PautaSimplificadaDTO(
        Integer idPauta,
        String nome,
        String descricao
) {
    public static PautaSimplificadaDTO pautaToPautaSimplificadaDTO(Pauta pauta) {

        return new PautaSimplificadaDTO(pauta.getId(),pauta.getNome(),pauta.getDescricao());
    }
}
