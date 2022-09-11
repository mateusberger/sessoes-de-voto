package com.desafio.sessoesdevoto.dominio.portas.repositorios;

import com.desafio.sessoesdevoto.dominio.Voto;

import java.util.List;

public interface VotoRepositoryPort {

    boolean existeVoto(String cpfDoAssociado, String idPauta);

    Voto registrarVoto(Voto voto);

    List<Voto> listarPeloIdDaPauta(String idPauta);
}
