package com.desafio.sessoesdevoto.dominio.excecoes;

public class SessaoJaExistenteException extends RuntimeException {

    public SessaoJaExistenteException() {

        super("Essa pauta já possui uma sessão registrada.");
    }
}