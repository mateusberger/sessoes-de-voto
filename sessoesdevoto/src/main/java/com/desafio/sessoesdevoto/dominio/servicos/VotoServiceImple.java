package com.desafio.sessoesdevoto.dominio.servicos;

import com.desafio.sessoesdevoto.dominio.Pauta;
import com.desafio.sessoesdevoto.dominio.dto.VotoCompletoDTO;
import com.desafio.sessoesdevoto.dominio.dto.RegistrarVotoForm;
import com.desafio.sessoesdevoto.dominio.dto.VotoSimplificadoDTO;
import com.desafio.sessoesdevoto.dominio.excecoes.PautaNaoEncotradaException;
import com.desafio.sessoesdevoto.dominio.excecoes.SessaoEncerradaException;
import com.desafio.sessoesdevoto.dominio.excecoes.SessaoNaoIniciadaException;
import com.desafio.sessoesdevoto.dominio.excecoes.VotoJaExistenteException;
import com.desafio.sessoesdevoto.dominio.portas.interfaces.VotoService;
import com.desafio.sessoesdevoto.dominio.portas.repositorios.PautaRepositoryPort;
import com.desafio.sessoesdevoto.dominio.portas.repositorios.VotoRepositoryPort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class VotoServiceImple implements VotoService {

    private final VotoRepositoryPort votoRepo;

    private final PautaRepositoryPort pautaRepo;

    public VotoServiceImple(VotoRepositoryPort votoRepo, PautaRepositoryPort pautaRepo) {
        this.votoRepo = votoRepo;
        this.pautaRepo = pautaRepo;
    }

    @Override
    public VotoCompletoDTO registrarVoto(RegistrarVotoForm form) {

        boolean existeVoto = votoRepo.existeVoto(form.cpfDoAssociado(), form.idPauta());

        if (existeVoto) throw new VotoJaExistenteException();

        Optional<Pauta> pautaOptional = pautaRepo.buscarPeloId(form.idPauta());

        if (pautaOptional.isEmpty()) throw new PautaNaoEncotradaException();

        Pauta pauta = pautaOptional.get();

        LocalDateTime agora = LocalDateTime.now();

        if (pauta.getInicioDaSessao().isAfter(agora)) throw new SessaoNaoIniciadaException();

        if (pauta.getTerminoDaSessao().isBefore(agora)) throw new SessaoEncerradaException();

        return VotoCompletoDTO.votoToVotoCompletoDTO(votoRepo.registrarVoto(form.toVoto()));
    }

    @Override
    public List<VotoSimplificadoDTO> listarVotosDeUmaPauta(String idPauta) {
        return votoRepo.listarPeloIdDaPauta(idPauta)
                .stream()
                .map(VotoSimplificadoDTO::votoToVotoSimplificadoDTO)
                .toList();
    }
}
