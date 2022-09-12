package com.desafio.sessoesdevoto.dominio.excecoes;

public class SessaoEncerradaException extends RuntimeException {

    public SessaoEncerradaException() {

        super("A sessão de votação já terminou");
    }
}