package com.desafio.sessoesdevoto.dominio.dto;

import com.desafio.sessoesdevoto.dominio.Pauta;
import com.desafio.sessoesdevoto.dominio.Voto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        Integer totalDeVotos,
        Integer totalDeVotosSim,
        Integer totalDeVotosNao,
        List<VotoSimplificadoDTO> votos
) {

    public static Clock clock = Clock.systemDefaultZone();

    public static PautaCompletaDTO pautaToPautaCompletaDTO(Pauta pauta) {

        int totalVotosSim = 0;
        int totalVotosNao = 0;

        List<VotoSimplificadoDTO> votosSimplificadoDTOS = new ArrayList<>();

        for (Voto voto : pauta.getVotos()) {
            if (voto.getVoto()) totalVotosSim++;
            else totalVotosNao++;

            votosSimplificadoDTOS.add(VotoSimplificadoDTO.votoToVotoSimplificadoDTO(voto));
        }

        LocalDateTime agora = LocalDateTime.now(clock);

        boolean votacaoIniciada = pauta.getInicioDaSessao() != null
                && agora.isAfter(pauta.getInicioDaSessao());

        boolean votacaoFinalizada = pauta.getTerminoDaSessao() != null
                && agora.isAfter(pauta.getTerminoDaSessao());

        return new PautaCompletaDTO(
                pauta.getId(),
                pauta.getNome(),
                pauta.getDescricao(),
                pauta.getInicioDaSessao(),
                pauta.getTerminoDaSessao(),
                votacaoIniciada,
                votacaoFinalizada,
                (totalVotosNao + totalVotosSim),
                totalVotosSim,
                totalVotosNao,
                votosSimplificadoDTOS
        );
    }
}
