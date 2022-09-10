package com.desafio.sessoesdevoto.dominio.servicos;

import com.desafio.sessoesdevoto.dominio.dto.VotoCompletoDTO;
import com.desafio.sessoesdevoto.dominio.dto.RegistrarVotoForm;
import com.desafio.sessoesdevoto.dominio.dto.VotoSimplificadoDTO;
import com.desafio.sessoesdevoto.dominio.portas.interfaces.VotoService;
import com.desafio.sessoesdevoto.dominio.portas.repositorios.VotoRepositoryPort;

import java.util.List;

public class VotoServiceImple implements VotoService {

    private final VotoRepositoryPort votoRepo;

    public VotoServiceImple(VotoRepositoryPort votoRepo) {
        this.votoRepo = votoRepo;
    }

    @Override
    public VotoCompletoDTO registrarVoto(RegistrarVotoForm form) throws Exception {

        boolean existe = votoRepo.existeVoto(form.cpfDoAssociado(), form.idPauta());
        if (existe) throw new IllegalArgumentException("Voto já computado para esse CPF associado nessa Pauta");

        return VotoCompletoDTO.votoToVotoCompletoDTO(votoRepo.registrarVoto(form.toVoto()));
    }

    @Override
    public List<VotoSimplificadoDTO> listarVotosDeUmaPauta(Integer idPauta) throws Exception {
        return votoRepo.listarPeloIdDaPauta(idPauta)
                .stream()
                .map(VotoSimplificadoDTO::votoToVotoSimplificadoDTO)
                .toList();
    }
}
