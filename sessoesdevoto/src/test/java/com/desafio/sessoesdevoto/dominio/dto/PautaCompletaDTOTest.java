package com.desafio.sessoesdevoto.dominio.dto;

import com.desafio.sessoesdevoto.dominio.Pauta;
import com.desafio.sessoesdevoto.dominio.Voto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

class PautaCompletaDTOTest {

    private Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

    @Test
    @DisplayName("Deveria aparecer que votação iniciada e votação não finalizada")
    void deveriaAparecerVotacaoEmCurso(){

        Pauta pauta = new Pauta(
                "idMockado",
                "nomeMockado",
                "descricaoMockado",
                LocalDateTime.now(clock).minusHours(1),
                LocalDateTime.now(clock).plusHours(1),
                new Voto("52431", true, "idMockado"),
                new Voto("74566", true, "idMockado")
        );

        PautaCompletaDTO.clock = clock;

        PautaCompletaDTO pautaCompletaDTO = PautaCompletaDTO.pautaToPautaCompletaDTO(pauta);

        assertTrue(pautaCompletaDTO.votacaoInicializada());
        assertFalse(pautaCompletaDTO.votacaoFinalizada());
    }

    @Test
    @DisplayName("Deveria aparecer votacao iniciada e votacao finalizada")
    void deveriaAparecerVotacaoIniciadaEVotacaoFinalizada(){

        Pauta pauta = new Pauta(
                "idMockado",
                "nomeMockado",
                "descricaoMockado",
                LocalDateTime.now(clock).minusHours(2),
                LocalDateTime.now(clock).minusHours(1),
                new Voto("52431", true, "idMockado"),
                new Voto("74566", true, "idMockado")
        );

        PautaCompletaDTO.clock = clock;

        PautaCompletaDTO pautaCompletaDTO = PautaCompletaDTO.pautaToPautaCompletaDTO(pauta);

        assertTrue(pautaCompletaDTO.votacaoInicializada());
        assertTrue(pautaCompletaDTO.votacaoFinalizada());
    }

    @Test
    @DisplayName("Deveria aparecer não votacao iniciada e votacao não finalizada")
    void deveriaAparecerVotacaoNaoIniciadaEVotacaoNaoFinalizada(){

        Pauta pauta = new Pauta(
                "idMockado",
                "nomeMockado",
                "descricaoMockado",
                LocalDateTime.now(clock).plusHours(1),
                LocalDateTime.now(clock).plusHours(2),
                new Voto("52431", true, "idMockado"),
                new Voto("74566", true, "idMockado")
        );

        PautaCompletaDTO.clock = clock;

        PautaCompletaDTO pautaCompletaDTO = PautaCompletaDTO.pautaToPautaCompletaDTO(pauta);

        assertFalse(pautaCompletaDTO.votacaoInicializada());
        assertFalse(pautaCompletaDTO.votacaoFinalizada());
    }

}