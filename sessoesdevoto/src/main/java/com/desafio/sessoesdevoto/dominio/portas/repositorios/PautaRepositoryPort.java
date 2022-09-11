package com.desafio.sessoesdevoto.dominio.portas.repositorios;

import com.desafio.sessoesdevoto.dominio.Pauta;

import java.util.List;
import java.util.Optional;

/**
 * Interface de persistencia de Pauta
 */
public interface PautaRepositoryPort {

    /**
     * Persiste uma pauta no repositório
     *
     * @param pauta Pauta a ser persistida
     * @return Dados registrados
     */
    Pauta registrarPauta(Pauta pauta);

    /**
     * Recupera uma Pauta através do ID
     *
     * @param idPauta ID da pauta
     * @return Pauta recuperada do repositorio
     */
    Optional<Pauta> buscarPeloId(String idPauta);

    /**
     * Altera os dados de uma Pauta
     *
     * @param pauta Pauta a ser persistida
     * @return Pauta persistida
     */
    Pauta alterarPauta(Pauta pauta);

    /**
     * Lista todas as Pautas do repositório
     *
     * @return Lista com todas as pautas recuperadas
     */
    List<Pauta> listarTodas();
}
