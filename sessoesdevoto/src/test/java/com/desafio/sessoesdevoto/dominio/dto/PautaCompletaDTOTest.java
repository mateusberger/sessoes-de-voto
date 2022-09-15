package com.desafio.sessoesdevoto.dominio.dto;

import com.desafio.sessoesdevoto.dominio.Pauta;
import com.desafio.sessoesdevoto.dominio.Voto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class PautaCompletaDTOTest {



    @Test
    @DisplayName("Deveria aparecer um conjunto igual de VotoSimplificadoDTO")
    void deveriaAparecerUmConjuntoIgualDeVotoSimplificadosDTO(){

        Pauta pauta = new Pauta(
                "IdMockado",
                "nomeMockado",
                "descricaoMockado",
                LocalDateTime.now(),
                LocalDateTime.now(),
                new Voto("97474338009", false, "idMockado"),
                new Voto("71824248008", true, "idMockado"),
                new Voto("68829007013", false, "idMockado")
        );

        PautaCompletaDTO dto = PautaCompletaDTO.fromPauta(pauta);

        assertEquals(3, dto.votos().size(), "Lista deveria estar com um tamanho igual");

        assertTrue(dto.votos().stream()
                .anyMatch(v -> Objects.equals(v.cpfDoAssociado(), "97474338009") && !v.voto()));

        assertTrue(dto.votos().stream()
                .anyMatch(v -> Objects.equals(v.cpfDoAssociado(), "71824248008") && v.voto()));

        assertTrue(dto.votos().stream()
                .anyMatch(v -> Objects.equals(v.cpfDoAssociado(), "68829007013") && !v.voto()));


    }

}