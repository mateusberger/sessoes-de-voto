package com.desafio.sessoesdevoto.dominio.excecoes;

public class VotoNaoPermitidoException extends RuntimeException {

    public VotoNaoPermitidoException() {

        super("Voto foi recusado.");
    }
}
