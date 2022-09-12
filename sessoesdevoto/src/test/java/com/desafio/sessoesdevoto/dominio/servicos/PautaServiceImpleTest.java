package com.desafio.sessoesdevoto.dominio.servicos;

import com.desafio.sessoesdevoto.dominio.Pauta;
import com.desafio.sessoesdevoto.dominio.form.IniciarSessaoForm;
import com.desafio.sessoesdevoto.dominio.dto.PautaSimplificadaDTO;
import com.desafio.sessoesdevoto.dominio.form.RegistrarPautaForm;
import com.desafio.sessoesdevoto.dominio.portas.repositorios.PautaRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class PautaServiceImpleTest {

    @Mock
    PautaRepositoryPort pautaRepoMock;

    PautaServiceImple pautaService;

    @BeforeEach
    void setup() {
        pautaRepoMock = Mockito.mock(PautaRepositoryPort.class);
        pautaService = new PautaServiceImple(pautaRepoMock);
    }

    @Test
    @DisplayName("Deveria cadastrar uma pauta")
    void deveriaCadastrarUmaPauta() {
        RegistrarPautaForm form = new RegistrarPautaForm("Pauta", "Descricao");

        Pauta pautaRetorno = form.toPauta();
        pautaRetorno.setId("idmockado");

        when(pautaRepoMock.registrarPauta(Mockito.any())).thenReturn(pautaRetorno);

        PautaSimplificadaDTO pautaDtoRetorno = pautaService.registrarPauta(form);

        assertEquals(pautaDtoRetorno.idPauta(), "idmockado");
        assertEquals(pautaDtoRetorno.nome(), "Pauta");
        assertEquals(pautaDtoRetorno.descricao(), "Descricao");
    }

    @Test
    @DisplayName("Deveria falhar ao cadastrar pauta sem nome")
    void deveriaFalharAoCadastrarPautaSemNome() {

        assertThrows(IllegalArgumentException.class, () -> {

            RegistrarPautaForm form = new RegistrarPautaForm(null, "Descricao");

            pautaService.registrarPauta(form);
        });
    }

    @Test
    @DisplayName("Deveria falhar ao cadastrar pauta com nome em branco")
    void deveriaFalharAoCadastrarPautaComNomeEmBranco() {

        assertThrows(IllegalArgumentException.class, () -> {

            RegistrarPautaForm form = new RegistrarPautaForm("", "Descricao");

            pautaService.registrarPauta(form);
        });
    }

    @Test
    @DisplayName("Deveria falhar ao cadastrar pauta sem descrição")
    void deveriaFalharAoCadastrarPautaSemDescricao() {

        assertThrows(IllegalArgumentException.class, () -> {

            RegistrarPautaForm form = new RegistrarPautaForm("Nome", null);

            pautaService.registrarPauta(form);
        });
    }

    @Test
    @DisplayName("Deveria falhar ao cadastrar pauta com descrição em branco")
    void deveriaFalharAoCadastrarPautaComDescricaoEmBranco() {

        assertThrows(IllegalArgumentException.class, () -> {

            RegistrarPautaForm form = new RegistrarPautaForm("Nome", "");

            pautaService.registrarPauta(form);
        });
    }

    @Test
    @DisplayName("Deveria iniciar sessão com inicio agora")
    void deveriaIniciarSessaoComInicioAgora(){

        Clock fixedClock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        IniciarSessaoForm.clock = fixedClock;

        IniciarSessaoForm form = new IniciarSessaoForm(
                "idDaPauta",
                null,
                LocalDateTime.now(fixedClock).plusHours(1)
        );

        assertEquals(form.inicioDaSessao(), LocalDateTime.now(fixedClock));
    }

    @Test
    @DisplayName("Deveria iniciar sessão com termino em um minuto")
    void deveriaIniciarSessaoComTerminoEmUmMinuto(){

        Clock fixedClock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        IniciarSessaoForm.clock = fixedClock;

        IniciarSessaoForm form = new IniciarSessaoForm(
                "idDaPauta",
                LocalDateTime.now(fixedClock),
                null
        );

        assertEquals(form.terminoDaSessao(), LocalDateTime.now(fixedClock).plus(1, ChronoUnit.MINUTES));
    }

}