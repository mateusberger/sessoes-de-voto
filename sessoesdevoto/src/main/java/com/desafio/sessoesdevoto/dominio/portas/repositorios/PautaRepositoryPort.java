package com.desafio.sessoesdevoto.dominio.portas.repositorios;

import com.desafio.sessoesdevoto.dominio.Pauta;

import java.util.List;
import java.util.Optional;

public interface PautaRepositoryPort {
    Pauta registrarPauta(Pauta pauta) throws Exception;

    Optional<Pauta> buscarPeloId(String idPauta) throws Exception;

    Pauta alterarPauta(Pauta pauta) throws Exception;

    List<Pauta> listarTodas() throws Exception;
}
