package com.desafio.sessoesdevoto.dominio.excecoes;

public class FalhaAoValidarPermissaoDeVotoException extends RuntimeException {

    public FalhaAoValidarPermissaoDeVotoException() {

        super("Falha ao validar permissão do voto");
    }
}
