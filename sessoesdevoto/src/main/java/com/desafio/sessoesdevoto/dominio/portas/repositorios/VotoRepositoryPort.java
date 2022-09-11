package com.desafio.sessoesdevoto.dominio.portas.repositorios;

import com.desafio.sessoesdevoto.dominio.Voto;

import java.util.List;

/**
 * Interface de persistencia de Voto
 */
public interface VotoRepositoryPort {

    /**
     * Verifica se existe um voto persistido com o CPF do associado e ID da pauta
     *
     * @param cpfDoAssociado Cpf para busca
     * @param idPauta ID pauta a ser buscada
     * @return true se o voto existe, false se não existe
     */
    boolean existeVoto(String cpfDoAssociado, String idPauta);

    /**
     * Persiste um voto no repositório
     *
     * @param voto Voto a ser persistido
     * @return Voto persistido
     */
    Voto registrarVoto(Voto voto);

    /**
     * Lista os Votos de uma pauta
     *
     * @param idPauta ID da Pauta a ser buscada
     * @return Lista com os ids da pauta
     */
    List<Voto> listarPeloIdDaPauta(String idPauta);
}
