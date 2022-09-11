package com.desafio.sessoesdevoto.aplicacao.exceptionhandler;

import com.desafio.sessoesdevoto.dominio.excecoes.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class TratamentoDeExcecoes {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MensagemDeErroPadrao> tratarIllegalArgumentException(
            IllegalArgumentException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(MensagemDeErroPadrao.criar(e,status));
    }

    @ExceptionHandler(PautaNaoEncotradaException.class)
    public ResponseEntity<MensagemDeErroPadrao> tratarPautaNaoEncotradaException(
            PautaNaoEncotradaException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(MensagemDeErroPadrao.criar(e,status));
    }

    @ExceptionHandler(SessaoEncerradaException.class)
    public ResponseEntity<MensagemDeErroPadrao> tratarSessaoEncerradaException(
            SessaoEncerradaException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        return ResponseEntity.status(status).body(MensagemDeErroPadrao.criar(e,status));
    }

    @ExceptionHandler(SessaoJaExistenteException.class)
    public ResponseEntity<MensagemDeErroPadrao> tratarSessaoJaExistenteException(
            SessaoJaExistenteException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        return ResponseEntity.status(status).body(MensagemDeErroPadrao.criar(e,status));
    }

    @ExceptionHandler(SessaoNaoIniciadaException.class)
    public ResponseEntity<MensagemDeErroPadrao> tratarSessaoNaoIniciadaException(
            SessaoNaoIniciadaException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        return ResponseEntity.status(status).body(MensagemDeErroPadrao.criar(e,status));
    }

    @ExceptionHandler(VotoJaExistenteException.class)
    public ResponseEntity<MensagemDeErroPadrao> tratarVotoJaExistenteException(
            VotoJaExistenteException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.CONFLICT;
        return ResponseEntity.status(status).body(MensagemDeErroPadrao.criar(e,status));
    }
}
