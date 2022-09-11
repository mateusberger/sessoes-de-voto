package com.desafio.sessoesdevoto.aplicacao;

import com.desafio.sessoesdevoto.dominio.dto.*;
import com.desafio.sessoesdevoto.dominio.portas.interfaces.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pauta")
public class PautaController {

    @Autowired
    private PautaService pautaService;

    @GetMapping
    public List<PautaSimplificadaDTO> listarPautas() {

        return pautaService.listarTodas();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PautaSimplificadaDTO registrarPauta(
            @RequestBody RegistrarPautaForm registrarPautaForm
    ) {

        PautaSimplificadaDTO pautaSimplificadaDTO = pautaService.registrarPauta(registrarPautaForm);

        return pautaService.registrarPauta(registrarPautaForm);
    }

    @PostMapping("/abrirVotacao")
    public SessaoInciadaDTO iniciarSessao(
            @RequestBody IniciarSessaoForm iniciarSessaoForm
    ) {

        return pautaService.registrarSessaoDeVoto(iniciarSessaoForm);
    }

    @GetMapping("/{idPauta}")
    public Optional<PautaCompletaDTO> visualizarPauta(
            @PathVariable String idPauta
    ) {

        return pautaService.buscarPautaPeloId(idPauta);
    }


}
