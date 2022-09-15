package com.desafio.sessoesdevoto.dominio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PautaTest {

    @Test
    @DisplayName("Deveria aparecer que votação iniciada e votação não finalizada")
    void deveriaAparecerVotacaoEmCurso(){

        Pauta pauta = new Pauta(
                "idMockado",
                "nomeMockado",
                "descricaoMockado",
                LocalDateTime.now().minusHours(1),
                LocalDateTime.now().plusHours(1),
                new Voto("65171016089", true, "idMockado"),
                new Voto("28787460076", true, "idMockado")
        );

        assertTrue(pauta.isSessaoIniciada());
        assertFalse(pauta.isSessaoTerminada());
    }

    @Test
    @DisplayName("Deveria aparecer votacao iniciada e votacao finalizada")
    void deveriaAparecerVotacaoIniciadaEVotacaoFinalizada(){

        Pauta pauta = new Pauta(
                "idMockado",
                "nomeMockado",
                "descricaoMockado",
                LocalDateTime.now().minusHours(2),
                LocalDateTime.now().minusHours(1),
                new Voto("65171016089", true, "idMockado"),
                new Voto("28787460076", true, "idMockado")
        );

        assertTrue(pauta.isSessaoIniciada());
        assertTrue(pauta.isSessaoTerminada());
    }

    @Test
    @DisplayName("Deveria aparecer não votacao iniciada e votacao não finalizada")
    void deveriaAparecerVotacaoNaoIniciadaEVotacaoNaoFinalizada(){

        Pauta pauta = new Pauta(
                "idMockado",
                "nomeMockado",
                "descricaoMockado",
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(2),
                new Voto("65171016089", true, "idMockado"),
                new Voto("28787460076", true, "idMockado")
        );

        assertFalse(pauta.isSessaoIniciada());
        assertFalse(pauta.isSessaoTerminada());
    }

    @Test
    @DisplayName("Deveria conter um total de dois votos")
    void deveriaConterUmTotalDeDoisVotos(){

        Pauta pauta = new Pauta(
                "idMockado",
                "nomeMockado",
                "descricaoMockado",
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(2),
                new Voto("65171016089", true, "idMockado"),
                new Voto("28787460076", true, "idMockado")
        );

        assertEquals(pauta.getTotalDeVotos(), 2);
    }

    @Test
    @DisplayName("Deveria conter um total de dois votos Sim")
    void deveriaConterUmTotalDeDoisVotosSim(){

        Pauta pauta = new Pauta(
                "idMockado",
                "nomeMockado",
                "descricaoMockado",
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(2),
                new Voto("65171016089", true, "idMockado"),
                new Voto("28787460076", true, "idMockado"),
                new Voto("76296792034", false, "idMockado")
        );

        assertEquals(pauta.getTotalDeVotosSim(), 2);
    }

    @Test
    @DisplayName("Deveria conter um total de um votos Não")
    void deveriaConterUmTotalDeUmVotosNao(){

        Pauta pauta = new Pauta(
                "idMockado",
                "nomeMockado",
                "descricaoMockado",
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(2),
                new Voto("65171016089", true, "idMockado"),
                new Voto("28787460076", true, "idMockado"),
                new Voto("76296792034", false, "idMockado")
        );

        assertEquals(pauta.getTotalDeVotosNao(), 1);
    }

}