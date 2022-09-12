package com.desafio.sessoesdevoto.aplicacao.exceptionhandler;

import com.desafio.sessoesdevoto.dominio.excecoes.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class TratamentoDeExcecoes {

    private static final Logger logger = LoggerFactory.getLogger(TratamentoDeExcecoes.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MensagemPadraoDeErro> tratarIllegalArgumentException(
            IllegalArgumentException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        logger.warn("Tratando erro de argumento inválido, retornando " + status.name());

        return ResponseEntity.status(status).body(MensagemPadraoDeErro.criar(e, status));
    }

    @ExceptionHandler(PautaNaoEncotradaException.class)
    public ResponseEntity<MensagemPadraoDeErro> tratarPautaNaoEncotradaException(
            PautaNaoEncotradaException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        logger.warn("Tratando erro de pauta não encontrada, retornando " + status.name());

        return ResponseEntity.status(status).body(MensagemPadraoDeErro.criar(e, status));
    }

    @ExceptionHandler(SessaoEncerradaException.class)
    public ResponseEntity<MensagemPadraoDeErro> tratarSessaoEncerradaException(
            SessaoEncerradaException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.FORBIDDEN;

        logger.warn("Tratando erro de sessão de votação já encerrada, retornando " + status.name());

        return ResponseEntity.status(status).body(MensagemPadraoDeErro.criar(e, status));
    }

    @ExceptionHandler(SessaoJaExistenteException.class)
    public ResponseEntity<MensagemPadraoDeErro> tratarSessaoJaExistenteException(
            SessaoJaExistenteException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.CONFLICT;

        logger.warn("Tratando erro de sessão já iniciada, retornando " + status.name());

        return ResponseEntity.status(status).body(MensagemPadraoDeErro.criar(e, status));
    }

    @ExceptionHandler(SessaoNaoIniciadaException.class)
    public ResponseEntity<MensagemPadraoDeErro> tratarSessaoNaoIniciadaException(
            SessaoNaoIniciadaException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.FORBIDDEN;

        logger.warn("Tratando erro de argumento inválido, retornando " + status.name());

        return ResponseEntity.status(status).body(MensagemPadraoDeErro.criar(e, status));
    }

    @ExceptionHandler(VotoJaExistenteException.class)
    public ResponseEntity<MensagemPadraoDeErro> tratarVotoJaExistenteException(
            VotoJaExistenteException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.CONFLICT;

        logger.warn("Tratando erro de voto já existente, retornando " + status.name());

        return ResponseEntity.status(status).body(MensagemPadraoDeErro.criar(e, status));
    }

    @ExceptionHandler(VotoNaoPermitidoException.class)
    public ResponseEntity<MensagemPadraoDeErro> tratarVotoNaoPermitidoException(
            VotoNaoPermitidoException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.FORBIDDEN;

        logger.error("Tratando erro de associado sem permissão para voto, retornando " + status.name());

        return ResponseEntity.status(status).body(MensagemPadraoDeErro.criar(e, status));
    }

    @ExceptionHandler(FalhaAoValidarPermissaoDeVotoException.class)
    public ResponseEntity<MensagemPadraoDeErro> tratarFalhaAoValidarPermissaoDeVotoException(
            FalhaAoValidarPermissaoDeVotoException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.BAD_GATEWAY;

        logger.error("Tratando erro inesperado ao tentar validar voto, retornando " + status.name(), e, request);

        return ResponseEntity.status(status).body(MensagemPadraoDeErro.criar(e, status));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensagemPadraoDeErro> tratarException(
            Exception e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        logger.error("Tratando erro inesperado, retornando " + status.name(), e, request);

        return ResponseEntity.status(status).body(MensagemPadraoDeErro.criar(e, status));
    }
}
