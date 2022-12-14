package com.desafio.sessoesdevoto.dominio.dto;

import com.desafio.sessoesdevoto.dominio.Pauta;

/**
 * Retorno com dados simplificados de uma pauta
 *
 * @param idPauta
 * @param nome
 * @param descricao
 */
public record PautaSimplificadaDTO(
        String idPauta,
        String nome,
        String descricao
) {
    public static PautaSimplificadaDTO pautaToPautaSimplificadaDTO(Pauta pauta) {

        return new PautaSimplificadaDTO(pauta.getId(),pauta.getNome(),pauta.getDescricao());
    }
}
