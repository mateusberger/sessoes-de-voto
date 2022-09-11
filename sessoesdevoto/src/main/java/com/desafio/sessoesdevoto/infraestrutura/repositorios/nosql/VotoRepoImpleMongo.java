package com.desafio.sessoesdevoto.infraestrutura.repositorios.nosql;

import com.desafio.sessoesdevoto.dominio.Voto;
import com.desafio.sessoesdevoto.dominio.excecoes.PautaNaoEncotradaException;
import com.desafio.sessoesdevoto.dominio.portas.repositorios.VotoRepositoryPort;
import com.desafio.sessoesdevoto.infraestrutura.entidades.PautaEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VotoRepoImpleMongo implements VotoRepositoryPort {

    @Autowired
    private PautaMongoRepository pautaRepo;

    @Override
    public boolean existeVoto(String cpfDoAssociado, String idPauta) {
        return pautaRepo.existsByIdAndVotosCpfDoAssociado(idPauta, cpfDoAssociado);
    }

    @Override
    public Voto registrarVoto(Voto voto) {

        Optional<PautaEntity> pautaEntityOptional = pautaRepo.findById(voto.getIdDaPauta());

        if (pautaEntityOptional.isEmpty()) throw new PautaNaoEncotradaException();

        PautaEntity pautaEntity = pautaEntityOptional.get();

        pautaEntity.getVotos().add(voto);

        pautaRepo.save(pautaEntity);

        return voto;
    }

    @Override
    public List<Voto> listarPeloIdDaPauta(String idPauta) {
        Optional<PautaEntity> pautaOptional = pautaRepo.findById(idPauta);

        if (pautaOptional.isEmpty()) return new ArrayList<>();

        return pautaOptional.get().getVotos();
    }
}
