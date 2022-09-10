package com.desafio.sessoesdevoto.dominio.servicos;

import com.desafio.sessoesdevoto.dominio.Pauta;
import com.desafio.sessoesdevoto.dominio.dto.*;
import com.desafio.sessoesdevoto.dominio.portas.interfaces.PautaService;
import com.desafio.sessoesdevoto.dominio.portas.repositorios.PautaRepositoryPort;

import java.util.List;
import java.util.Optional;

public class PautaServiceImple implements PautaService {

    private final PautaRepositoryPort pautaRepo;

    public PautaServiceImple(PautaRepositoryPort pautaRepo) {
        this.pautaRepo = pautaRepo;
    }


    @Override
    public PautaSimplificadaDTO registrarPauta(RegistrarPautaForm form) throws Exception {
        Pauta pauta = pautaRepo.registrarPauta(form.toPauta());
        return PautaSimplificadaDTO.pautaToPautaSimplificadaDTO(pauta);
    }

    @Override
    public SessaoInciadaDTO registrarSessaoDeVoto(IniciarSessaoForm form) throws Exception {
        Optional<Pauta> pautaOptional = pautaRepo.buscarPeloId(form.idPauta());

        if (pautaOptional.isEmpty()) throw new IllegalArgumentException("Id da pauta informado não foi localizado");

        Pauta pauta = pautaOptional.get();

        if (pauta.getInicioDaSessao() != null || pauta.getTerminoDaSessao() != null)
            throw new IllegalArgumentException("Pauta já possui uma sessão");

        pauta.setInicioDaSessao(form.inicioDaSessao());
        pauta.setTerminoDaSessao(form.terminoDaSessao());

        Pauta pautaPersistida = pautaRepo.alterarPauta(pauta);

        return SessaoInciadaDTO.pautaToSessaoIniciadaDTO(pautaPersistida);
    }

    @Override
    public Optional<PautaCompletaDTO> buscarPautaPeloId(Integer idPauta) throws Exception {
        return pautaRepo.buscarPeloId(idPauta).map(PautaCompletaDTO::pautaToPautaCompletaDTO);
    }

    @Override
    public List<PautaSimplificadaDTO> listarTodas() throws Exception {
        return pautaRepo.listarTodas()
                .stream()
                .map(PautaSimplificadaDTO::pautaToPautaSimplificadaDTO)
                .toList();
    }
}
