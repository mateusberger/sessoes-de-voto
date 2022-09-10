package com.desafio.sessoesdevoto.dominio.portas.interfaces;

import com.desafio.sessoesdevoto.dominio.dto.*;

import java.util.List;
import java.util.Optional;

public interface PautaService {

    PautaSimplificadaDTO registrarPauta(RegistrarPautaForm form) throws Exception;

    SessaoInciadaDTO registrarSessaoDeVoto(IniciarSessaoForm form) throws Exception;

    Optional<PautaCompletaDTO> buscarPautaPeloId(String idPauta) throws Exception;

    List<PautaSimplificadaDTO> listarTodas() throws Exception;

}
