package com.desafio.sessoesdevoto.dominio.dto;

import com.desafio.sessoesdevoto.dominio.Pauta;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Retorno com todas as informações de uma pauta
 *
 * @param idPauta
 * @param nome
 * @param descricao
 * @param inicioDaSessao
 * @param terminoDaSessao
 * @param votacaoFinalizada
 * @param totalDeVotos
 * @param totalDeVotosSim
 * @param totalDeVotosNao
 * @param votos
 */
public record PautaCompletaDTO(
        String idPauta,
        String nome,
        String descricao,
        LocalDateTime inicioDaSessao,
        LocalDateTime terminoDaSessao,
        Boolean votacaoInicializada,
        Boolean votacaoFinalizada,
        Long totalDeVotos,
        Long totalDeVotosSim,
        Long totalDeVotosNao,
        List<VotoSimplificadoDTO> votos
) {

    public static PautaCompletaDTO fromPauta(Pauta pauta) {

        return new PautaCompletaDTO(
                pauta.getId(),
                pauta.getNome(),
                pauta.getDescricao(),
                pauta.getInicioDaSessao(),
                pauta.getTerminoDaSessao(),
                pauta.isSessaoIniciada(),
                pauta.isSessaoTerminada(),
                pauta.getTotalDeVotos(),
                pauta.getTotalDeVotosSim(),
                pauta.getTotalDeVotosNao(),
                pauta.getVotos()
                        .stream()
                        .map(VotoSimplificadoDTO::votoToVotoSimplificadoDTO)
                        .toList()
        );
    }
}
