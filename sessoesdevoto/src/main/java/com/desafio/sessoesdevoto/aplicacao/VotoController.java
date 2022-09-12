package com.desafio.sessoesdevoto.aplicacao;

import com.desafio.sessoesdevoto.aplicacao.exceptionhandler.MensagemPadraoDeErro;
import com.desafio.sessoesdevoto.dominio.dto.VotoCompletoDTO;
import com.desafio.sessoesdevoto.dominio.form.RegistrarVotoForm;
import com.desafio.sessoesdevoto.dominio.portas.interfaces.VotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//<editor-fold defaultstate="collapsed" desc="Swagger Doc">
@Tag(
        name = "Voto",
        description = "Controladora para registro de votos"
)
//</editor-fold>
@RestController
@RequestMapping("/voto")
public class VotoController {

    @Autowired
    private VotoService votoService;

    //<editor-fold defaultstate="collapsed" desc="Doc">
    @Operation(
            summary = "Registrar um voto",
            description =
                    "Registra um voto utilizando o CPF do associado e o ID da pauta.\n\n" +
                    "O atributo voto contem a resposta do voto (true = SIM, false = NÃO).",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Voto registrado com sucesso",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = VotoCompletoDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Atributos ou valores incorretos",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = MensagemPadraoDeErro.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Sessão de votação encerrada ou ainda não iniciada",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = MensagemPadraoDeErro.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Voto já computado",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = MensagemPadraoDeErro.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno no servidor",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = MensagemPadraoDeErro.class)
                                    )
                            }
                    )
            }
    )
    //</editor-fold>
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VotoCompletoDTO registrarVoto(
            @RequestBody RegistrarVotoForm registrarVotoForm
    ) {
        return votoService.registrarVoto(registrarVotoForm);
    }
}
