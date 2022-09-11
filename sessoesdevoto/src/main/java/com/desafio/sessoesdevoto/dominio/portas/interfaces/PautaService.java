package com.desafio.sessoesdevoto.dominio.portas.interfaces;

import com.desafio.sessoesdevoto.dominio.dto.*;

import java.util.List;
import java.util.Optional;

/**
 * Classe de serviço, para registro e controle de Pautas
 */
public interface PautaService {

    /**
     * Registra e persiste uma nova Pauta
     *
     * @param form Formulário com os dados de registro da Pauta
     * @return Dados simplificados sobre o registro dessa Pauta
     */
    PautaSimplificadaDTO registrarPauta(RegistrarPautaForm form);

    /**
     * Inicia ou agenda uma sessão de votos de uma pauta
     *
     * @param form Dados sobre a pauta e momento de início e termino da sessão
     * @return Dados que foram registrados
     */
    SessaoInciadaDTO registrarSessaoDeVoto(IniciarSessaoForm form);

    /**
     * Busca uma pauta através do ID
     *
     * @param idPauta ID da Pauta
     * @return Dados localizados ou optinal vazio quando nada for localizado
     */
    Optional<PautaCompletaDTO> buscarPautaPeloId(String idPauta);

    /**
     * Lista de forma simplificada todas as pautas
     *
     * @return Lista com todas as pautas registradas
     */
    List<PautaSimplificadaDTO> listarTodas();

}
