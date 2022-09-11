package com.desafio.sessoesdevoto.dominio.portas.repositorios;

import com.desafio.sessoesdevoto.dominio.Pauta;

import java.util.List;
import java.util.Optional;

public interface PautaRepositoryPort {
    Pauta registrarPauta(Pauta pauta);

    Optional<Pauta> buscarPeloId(String idPauta);

    Pauta alterarPauta(Pauta pauta);

    List<Pauta> listarTodas();
}
