package com.desafio.sessoesdevoto.dominio.servicos;

import com.desafio.sessoesdevoto.dominio.Pauta;
import com.desafio.sessoesdevoto.dominio.dto.VotoCompletoDTO;
import com.desafio.sessoesdevoto.dominio.excecoes.*;
import com.desafio.sessoesdevoto.dominio.form.RegistrarVotoForm;
import com.desafio.sessoesdevoto.dominio.dto.VotoSimplificadoDTO;
import com.desafio.sessoesdevoto.dominio.portas.interfaces.PermissorDeVoto;
import com.desafio.sessoesdevoto.dominio.portas.interfaces.VotoService;
import com.desafio.sessoesdevoto.dominio.portas.repositorios.PautaRepositoryPort;
import com.desafio.sessoesdevoto.dominio.portas.repositorios.VotoRepositoryPort;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class VotoServiceImple implements VotoService {

    private final VotoRepositoryPort votoRepo;

    private final PautaRepositoryPort pautaRepo;

    private final PermissorDeVoto permissorDeVoto;

    private final Clock clock;

    public VotoServiceImple(
            VotoRepositoryPort votoRepo,
            PautaRepositoryPort pautaRepo,
            PermissorDeVoto permissorDeVoto,
            Clock clock
    ) {

        this.votoRepo = votoRepo;
        this.pautaRepo = pautaRepo;
        this.permissorDeVoto = permissorDeVoto;
        this.clock = clock;
    }

    @Override
    public VotoCompletoDTO registrarVoto(RegistrarVotoForm form) {

        boolean existeVoto = votoRepo.existeVoto(form.cpfDoAssociado(), form.idPauta());

        if (existeVoto) throw new VotoJaExistenteException();

        Optional<Pauta> pautaOptional = pautaRepo.buscarPeloId(form.idPauta());

        if (pautaOptional.isEmpty()) throw new PautaNaoEncotradaException();

        Pauta pauta = pautaOptional.get();

        LocalDateTime agora = LocalDateTime.now(clock);

        if (pauta.getInicioDaSessao() == null || pauta.getTerminoDaSessao() == null) {
            throw new SessaoNaoIniciadaException();
        }

        if (!pauta.isSessaoIniciada()) {
            throw new SessaoNaoIniciadaException();
        }

        if (pauta.isSessaoTerminada()) {
            throw new SessaoEncerradaException();
        }

        boolean naoPermiteVoto = !permissorDeVoto.permiteVoto(form.cpfDoAssociado());

        if (naoPermiteVoto) throw new VotoNaoPermitidoException();

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