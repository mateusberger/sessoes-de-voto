package com.desafio.sessoesdevoto.dominio.dto;

import com.desafio.sessoesdevoto.dominio.Voto;

/**
 * Retorno simplificado de um voto
 *
 * @param cpfDoAssociado
 * @param voto
 */
public record VotoSimplificadoDTO(
        String cpfDoAssociado,
        boolean voto
) {
    public static VotoSimplificadoDTO votoToVotoSimplificadoDTO(Voto voto) {
        return new VotoSimplificadoDTO(voto.getCpfDoAssociado(), voto.getVoto());
    }
}
