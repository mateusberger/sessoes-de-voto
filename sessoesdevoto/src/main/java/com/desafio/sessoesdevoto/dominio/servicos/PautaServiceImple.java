package com.desafio.sessoesdevoto.dominio.servicos;

import com.desafio.sessoesdevoto.dominio.Pauta;
import com.desafio.sessoesdevoto.dominio.dto.*;
import com.desafio.sessoesdevoto.dominio.excecoes.PautaNaoEncotradaException;
import com.desafio.sessoesdevoto.dominio.excecoes.SessaoJaExistenteException;
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
    public PautaSimplificadaDTO registrarPauta(RegistrarPautaForm form) {
        Pauta pauta = pautaRepo.registrarPauta(form.toPauta());
        return PautaSimplificadaDTO.pautaToPautaSimplificadaDTO(pauta);
    }

    @Override
    public SessaoInciadaDTO registrarSessaoDeVoto(IniciarSessaoForm form) {
        Optional<Pauta> pautaOptional = pautaRepo.buscarPeloId(form.idPauta());

        if (pautaOptional.isEmpty()) throw new PautaNaoEncotradaException();

        Pauta pauta = pautaOptional.get();

        if (pauta.getInicioDaSessao() != null || pauta.getTerminoDaSessao() != null)
            throw new SessaoJaExistenteException();

        pauta.setInicioDaSessao(form.inicioDaSessao());
        pauta.setTerminoDaSessao(form.terminoDaSessao());

        Pauta pautaPersistida = pautaRepo.alterarPauta(pauta);

        return SessaoInciadaDTO.pautaToSessaoIniciadaDTO(pautaPersistida);
    }

    @Override
    public Optional<PautaCompletaDTO> buscarPautaPeloId(String idPauta) {
        return pautaRepo.buscarPeloId(idPauta).map(PautaCompletaDTO::pautaToPautaCompletaDTO);
    }

    @Override
    public List<PautaSimplificadaDTO> listarTodas() {
        return pautaRepo.listarTodas()
                .stream()
                .map(PautaSimplificadaDTO::pautaToPautaSimplificadaDTO)
                .toList();
    }
}
