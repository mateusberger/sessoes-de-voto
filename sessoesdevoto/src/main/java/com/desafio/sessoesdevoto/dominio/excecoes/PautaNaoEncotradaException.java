package com.desafio.sessoesdevoto.dominio.excecoes;

public class PautaNaoEncotradaException extends RuntimeException {

    public PautaNaoEncotradaException() {

        super("Não foram encontrados registros dessa pauta.");
    }
}
