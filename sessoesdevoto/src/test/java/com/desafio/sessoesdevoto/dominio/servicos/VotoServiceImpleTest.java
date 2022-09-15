package com.desafio.sessoesdevoto.dominio.servicos;

import com.desafio.sessoesdevoto.dominio.Pauta;
import com.desafio.sessoesdevoto.dominio.Voto;
import com.desafio.sessoesdevoto.dominio.dto.VotoCompletoDTO;
import com.desafio.sessoesdevoto.dominio.excecoes.*;
import com.desafio.sessoesdevoto.dominio.form.RegistrarVotoForm;
import com.desafio.sessoesdevoto.dominio.portas.interfaces.PermissorDeVoto;
import com.desafio.sessoesdevoto.dominio.portas.interfaces.VotoService;
import com.desafio.sessoesdevoto.dominio.portas.repositorios.PautaRepositoryPort;
import com.desafio.sessoesdevoto.dominio.portas.repositorios.VotoRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class VotoServiceImpleTest {

    private VotoService votoService;

    private VotoRepositoryPort votoRepoMock;

    private PautaRepositoryPort pautaRepoMock;

    private PermissorDeVoto permissorMock;

    private Clock clockMock;

    @BeforeEach
    void setup() {

        votoRepoMock = Mockito.mock(VotoRepositoryPort.class);

        pautaRepoMock = Mockito.mock(PautaRepositoryPort.class);

        permissorMock = Mockito.mock(PermissorDeVoto.class);

        clockMock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        votoService = new VotoServiceImple(votoRepoMock, pautaRepoMock, permissorMock, clockMock);
    }

    @Test
    @DisplayName("Deveria falhar por voto não permitido")
    void deveriaFalharPorVotoNaoPermitido() {

        assertThrows(VotoNaoPermitidoException.class, () -> {

            RegistrarVotoForm votoForm = new RegistrarVotoForm("idPauta", "11102579025", true);

            Pauta pauta = new Pauta(
                    "idPauta",
                    "nomePauta",
                    "descricaoPauta",
                    LocalDateTime.now(clockMock).minusHours(1),
                    LocalDateTime.now(clockMock).plusHours(1),
                    new Voto("44452061052", false, "idPauta")
            );

            when(votoRepoMock.existeVoto(Mockito.any(), Mockito.any())).thenReturn(false);

            when(pautaRepoMock.buscarPeloId(Mockito.any())).thenReturn(Optional.of(pauta));

            when(permissorMock.permiteVoto(votoForm.cpfDoAssociado())).thenReturn(false);

            votoService.registrarVoto(votoForm);
        });
    }

    @Test
    @DisplayName("Deveria falhar por voto já existente")
    void deveriaFalharPorVotoJaExistente() {

        assertThrows(VotoJaExistenteException.class, () -> {

            RegistrarVotoForm votoForm = new RegistrarVotoForm("idPauta", "11102579025", true);

            Pauta pauta = new Pauta(
                    "idPauta",
                    "nomePauta",
                    "descricaoPauta",
                    LocalDateTime.now(clockMock).minusHours(1),
                    LocalDateTime.now(clockMock).plusHours(1),
                    new Voto("11102579025", false, "idPauta")
            );

            when(votoRepoMock.existeVoto(Mockito.any(), Mockito.any())).thenReturn(true);

            when(pautaRepoMock.buscarPeloId(Mockito.any())).thenReturn(Optional.of(pauta));

            when(permissorMock.permiteVoto(votoForm.cpfDoAssociado())).thenReturn(true);

            votoService.registrarVoto(votoForm);
        });
    }

    @Test
    @DisplayName("Deveria falhar por pauta não encontrada")
    void deveriaFalharPorPautaNaoEncontrada() {

        assertThrows(PautaNaoEncotradaException.class, () -> {

            RegistrarVotoForm votoForm = new RegistrarVotoForm("idPauta", "11102579025", true);

            Pauta pauta = new Pauta(
                    "idPauta",
                    "nomePauta",
                    "descricaoPauta",
                    LocalDateTime.now(clockMock).minusHours(1),
                    LocalDateTime.now(clockMock).plusHours(1),
                    new Voto("44452061052", false, "idPauta")
            );

            when(votoRepoMock.existeVoto(Mockito.any(), Mockito.any())).thenReturn(false);

            when(pautaRepoMock.buscarPeloId(Mockito.any())).thenReturn(Optional.empty());

            when(permissorMock.permiteVoto(votoForm.cpfDoAssociado())).thenReturn(true);

            votoService.registrarVoto(votoForm);
        });
    }

    @Test
    @DisplayName("Deveria falhar por sessão já encerrada")
    void deveriaFalharPorPorSessaoJaEncerrada() {

        assertThrows(SessaoEncerradaException.class, () -> {

            RegistrarVotoForm votoForm = new RegistrarVotoForm("idPauta", "11102579025", true);

            Pauta pauta = new Pauta(
                    "idPauta",
                    "nomePauta",
                    "descricaoPauta",
                    LocalDateTime.now(clockMock).minusHours(2),
                    LocalDateTime.now(clockMock).minusHours(1)
            );

            when(votoRepoMock.existeVoto(Mockito.any(), Mockito.any())).thenReturn(false);

            when(pautaRepoMock.buscarPeloId(Mockito.any())).thenReturn(Optional.of(pauta));

            when(permissorMock.permiteVoto(votoForm.cpfDoAssociado())).thenReturn(true);

            votoService.registrarVoto(votoForm);
        });
    }

    @Test
    @DisplayName("Deveria falhar por sessão não iniciada")
    void deveriaFalharPorSessaoNaoIniciada() {

        assertThrows(SessaoNaoIniciadaException.class, () -> {

            RegistrarVotoForm votoForm = new RegistrarVotoForm("idPauta", "11102579025", true);

            Pauta pauta = new Pauta(
                    "idPauta",
                    "nomePauta",
                    "descricaoPauta",
                    LocalDateTime.now(clockMock).plusHours(1),
                    LocalDateTime.now(clockMock).plusHours(2)
            );

            when(votoRepoMock.existeVoto(Mockito.any(), Mockito.any())).thenReturn(false);

            when(pautaRepoMock.buscarPeloId(Mockito.any())).thenReturn(Optional.of(pauta));

            when(permissorMock.permiteVoto(votoForm.cpfDoAssociado())).thenReturn(true);

            votoService.registrarVoto(votoForm);
        });
    }

    @Test
    @DisplayName("Deveria registrar voto")
    void deveriaRegistrarVoto() {

        RegistrarVotoForm votoForm = new RegistrarVotoForm("idPauta", "11102579025", true);

        Pauta pauta = new Pauta(
                "idPauta",
                "nomePauta",
                "descricaoPauta",
                LocalDateTime.now(clockMock).minusHours(1),
                LocalDateTime.now(clockMock).plusHours(2)
        );

        when(votoRepoMock.existeVoto(Mockito.any(), Mockito.any())).thenReturn(false);

        when(votoRepoMock.registrarVoto(Mockito.any())).thenReturn(votoForm.toVoto());

        when(pautaRepoMock.buscarPeloId(Mockito.any())).thenReturn(Optional.of(pauta));

        when(permissorMock.permiteVoto(votoForm.cpfDoAssociado())).thenReturn(true);

        VotoCompletoDTO votoCompletoDTO = votoService.registrarVoto(votoForm);

        assertEquals("11102579025", votoCompletoDTO.cpfAssociado());
        assertEquals(true, votoCompletoDTO.voto());
        assertEquals("idPauta", votoCompletoDTO.idDaPauta());

    }

}