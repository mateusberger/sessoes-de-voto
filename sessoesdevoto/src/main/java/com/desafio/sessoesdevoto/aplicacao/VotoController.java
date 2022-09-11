package com.desafio.sessoesdevoto.aplicacao;

import com.desafio.sessoesdevoto.dominio.dto.RegistrarVotoForm;
import com.desafio.sessoesdevoto.dominio.portas.interfaces.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voto")
public class VotoController {

    @Autowired
    private VotoService votoService;

    @PostMapping
    public ResponseEntity registrarVoto(
            @RequestBody RegistrarVotoForm registrarVotoForm
    ) {

        return ResponseEntity.ok(votoService.registrarVoto(registrarVotoForm));
    }
}
