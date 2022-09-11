package com.desafio.sessoesdevoto.aplicacao;

import com.desafio.sessoesdevoto.aplicacao.exceptionhandler.MensagemDeErroPadrao;
import com.desafio.sessoesdevoto.dominio.dto.PautaCompletaDTO;
import com.desafio.sessoesdevoto.dominio.dto.PautaSimplificadaDTO;
import com.desafio.sessoesdevoto.dominio.dto.SessaoInciadaDTO;
import com.desafio.sessoesdevoto.dominio.form.IniciarSessaoForm;
import com.desafio.sessoesdevoto.dominio.form.RegistrarPautaForm;
import com.desafio.sessoesdevoto.dominio.portas.interfaces.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//<editor-fold defaultstate="collapsed" desc="Swagger Doc">
@Tag(
        name = "Pauta",
        description = "Controladora para gerir a criação de pautas e iniciar sessões de votação"
)
//</editor-fold>
@RestController
@RequestMapping("/pauta")
public class PautaController {

    @Autowired
    private PautaService pautaService;

    //<editor-fold defaultstate="collapsed" desc="Doc">
    @Operation(
            summary = "Listar pautas",
            description =
                    "Lista todas as pautas registradas.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista com todas as pautas",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = PautaSimplificadaDTO.class)
                                            )
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno no servidor",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = MensagemDeErroPadrao.class)
                                    )
                            }
                    )
            }
    )
    //</editor-fold>
    @GetMapping
    public List<PautaSimplificadaDTO> listarPautas() {
        return pautaService.listarTodas();
    }


    //<editor-fold defaultstate="collapsed" desc="Doc">
    @Operation(
            summary = "Registra uma Pauta",
            description =
                    "Registra uma pauta para a votação utilizando um nome e descrição.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Pauta registrada com sucesso",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = PautaSimplificadaDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Atributos ou valores incorretos",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = MensagemDeErroPadrao.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno no servidor",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = MensagemDeErroPadrao.class)
                                    )
                            }
                    )
            }
    )
    //</editor-fold>
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PautaSimplificadaDTO registrarPauta(
            @RequestBody RegistrarPautaForm registrarPautaForm
    ) {
        return pautaService.registrarPauta(registrarPautaForm);
    }

    //<editor-fold defaultstate="collapsed" desc="Doc">
    @Operation(
            summary = "Abrir/agendar uma Sessão de votação",
            description =
                    "Abre ou agenda uma sessão de votação de uma pauta.\n\n" +
                            "Para abrir a sessão no momento atual, não informe o campo 'inicioDaSessao'.\n\n" +
                            "Caso o campo 'terminoDaSessao' não seja informado, a sessão ira durar 1 minuto.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Sessão registrada com sucesso",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = SessaoInciadaDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Atributos ou valores incorretos",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = MensagemDeErroPadrao.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Pauta já possuí uma sessão",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = MensagemDeErroPadrao.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno no servidor",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = MensagemDeErroPadrao.class)
                                    )
                            }
                    )
            }
    )
    //</editor-fold>
    @PostMapping("/abrirVotacao")
    public SessaoInciadaDTO iniciarSessao(
            @RequestBody IniciarSessaoForm iniciarSessaoForm
    ) {
        return pautaService.registrarSessaoDeVoto(iniciarSessaoForm);
    }

    //<editor-fold defaultstate="collapsed" desc="Doc">
    @Operation(
            summary = "Visualizar pauta",
            description =
                    "Retorna todas as informações de uma Pauta se o ID informado existir.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Sessão registrada com sucesso",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = PautaCompletaDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno no servidor",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = MensagemDeErroPadrao.class)
                                    )
                            }
                    )
            }
    )
    //</editor-fold>
    @GetMapping("/{idPauta}")
    public Optional<PautaCompletaDTO> visualizarPauta(
            @PathVariable String idPauta
    ) {
        return pautaService.buscarPautaPeloId(idPauta);
    }


}
