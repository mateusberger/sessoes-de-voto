package com.desafio.sessoesdevoto.aplicacao.exceptionhandler;

import org.springframework.http.HttpStatus;

/**
 * Padrão de mensagem de erro da api.
 *
 * @param mensagem
 * @param status
 * @param statusMessage
 * @param timeStamp
 */
public record MensagemDeErroPadrao(
        String mensagem,
        Integer status,
        String statusMessage,
        Long timeStamp
) {

    public static MensagemDeErroPadrao criar(Throwable ex, HttpStatus status){
        return new MensagemDeErroPadrao(
                ex.getMessage(),
                status.value(),
                status.name(),
                System.currentTimeMillis()
        );
    }
}
