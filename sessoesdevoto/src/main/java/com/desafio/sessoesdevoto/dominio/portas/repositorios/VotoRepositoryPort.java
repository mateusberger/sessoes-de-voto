package com.desafio.sessoesdevoto.dominio.portas.repositorios;

import com.desafio.sessoesdevoto.dominio.Voto;

import java.util.List;

public interface VotoRepositoryPort {

    boolean existeVoto(String cpfDoAssociado, Integer idPauta) throws Exception;

    Voto registrarVoto(Voto voto) throws Exception;

    List<Voto> listarPeloIdDaPauta(Integer idPauta) throws Exception;
}
