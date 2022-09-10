package com.desafio.sessoesdevoto.dominio.portas.interfaces;

import com.desafio.sessoesdevoto.dominio.dto.RegistrarVotoForm;
import com.desafio.sessoesdevoto.dominio.dto.VotoCompletoDTO;
import com.desafio.sessoesdevoto.dominio.dto.VotoSimplificadoDTO;

import java.util.List;

public interface VotoService {

    VotoCompletoDTO registrarVoto(RegistrarVotoForm registrarVotoForm) throws Exception;

    List<VotoSimplificadoDTO> listarVotosDeUmaPauta(String idPauta) throws Exception;

}
