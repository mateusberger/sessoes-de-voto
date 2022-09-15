package com.desafio.sessoesdevoto.dominio.excecoes;

public class VotoJaExistenteException extends RuntimeException {

    public VotoJaExistenteException() {

        super("Voto já computado para esse associado nessa pauta.");
    }
}