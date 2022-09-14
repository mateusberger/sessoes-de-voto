package com.desafio.sessoesdevoto.dominio.excecoes;

public class SessaoNaoIniciadaException extends RuntimeException {

    public SessaoNaoIniciadaException() {

        super("A sessão de votação não foi iniciada.");
    }
}
