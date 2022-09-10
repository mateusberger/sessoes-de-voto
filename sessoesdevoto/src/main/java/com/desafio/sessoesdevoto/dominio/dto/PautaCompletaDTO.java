package com.desafio.sessoesdevoto.dominio.dto;

import com.desafio.sessoesdevoto.dominio.Pauta;
import com.desafio.sessoesdevoto.dominio.Voto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record PautaCompletaDTO(
        Integer idPauta,
        String nome,
        String descricao,
        LocalDateTime inicioDaSessao,
        LocalDateTime terminoDaSessao,
        Integer totalDeVotos,
        Integer totalDeVotosSim,
        Integer totalDeVotosNao,
        List<VotoSimplificadoDTO> votos
) {
    public static PautaCompletaDTO pautaToPautaCompletaDTO(Pauta pauta) {

        int totalVotosSim = 0;
        int totalVotosNao = 0;

        List<VotoSimplificadoDTO> votosSimplificadoDTOS = new ArrayList<>();

        for (Voto voto : pauta.getVotos()) {
            if (voto.getVoto()) totalVotosSim++;
            else totalVotosNao++;

            votosSimplificadoDTOS.add(VotoSimplificadoDTO.votoToVotoSimplificadoDTO(voto));
        }

        return new PautaCompletaDTO(
                pauta.getId(),
                pauta.getNome(),
                pauta.getDescricao(),
                pauta.getInicioDaSessao(),
                pauta.getTerminoDaSessao(),
                (totalVotosNao + totalVotosSim),
                totalVotosSim,
                totalVotosNao,
                votosSimplificadoDTOS
        );
    }
}
