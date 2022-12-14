package com.desafio.sessoesdevoto.dominio.dto;

import com.desafio.sessoesdevoto.dominio.Voto;

/**
 * Retorno completo de um voto
 *
 * @param idDaPauta
 * @param cpfAssociado
 * @param voto
 */
public record VotoCompletoDTO(
        String idDaPauta,
        String cpfAssociado,
        boolean voto
) {

    public static VotoCompletoDTO votoToVotoCompletoDTO(Voto voto) {
        return new VotoCompletoDTO(voto.getIdDaPauta(), voto.getCpfDoAssociado(), voto.getVoto());
    }
}