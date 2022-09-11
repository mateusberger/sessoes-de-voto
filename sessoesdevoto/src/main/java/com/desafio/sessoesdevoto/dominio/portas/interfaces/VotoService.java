package com.desafio.sessoesdevoto.dominio.portas.interfaces;

import com.desafio.sessoesdevoto.dominio.dto.RegistrarVotoForm;
import com.desafio.sessoesdevoto.dominio.dto.VotoCompletoDTO;
import com.desafio.sessoesdevoto.dominio.dto.VotoSimplificadoDTO;

import java.util.List;

/**
 * Classe de serviço, para registro e controle de votos em uma pauta
 */
public interface VotoService {

    /**
     * Registra o voto de um associado em uma pauta
     *
     * @param registrarVotoForm Formulário com os dados do voto a ser registrado
     * @return Dados registrados do voto (incluindo id da pauta)
     */
    VotoCompletoDTO registrarVoto(RegistrarVotoForm registrarVotoForm);

    /**
     * Lista todos os votos de uma pauta
     *
     * @param idPauta ID da pauta
     * @return Lista com os votos registrados
     */
    List<VotoSimplificadoDTO> listarVotosDeUmaPauta(String idPauta);

}
