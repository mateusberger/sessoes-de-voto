package com.desafio.sessoesdevoto.dominio.portas.interfaces;

import com.desafio.sessoesdevoto.dominio.dto.*;

import java.util.List;
import java.util.Optional;

public interface PautaService {

    PautaSimplificadaDTO registrarPauta(RegistrarPautaForm form);

    SessaoInciadaDTO registrarSessaoDeVoto(IniciarSessaoForm form);

    Optional<PautaCompletaDTO> buscarPautaPeloId(String idPauta);

    List<PautaSimplificadaDTO> listarTodas();

}
