package com.desafio.sessoesdevoto.infraestrutura.repositorios.nosql;

import com.desafio.sessoesdevoto.dominio.Voto;
import com.desafio.sessoesdevoto.dominio.excecoes.PautaNaoEncotradaException;
import com.desafio.sessoesdevoto.dominio.portas.repositorios.VotoRepositoryPort;
import com.desafio.sessoesdevoto.infraestrutura.entidades.PautaEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VotoRepoImpleMongo implements VotoRepositoryPort {

    private static final Logger logger = LoggerFactory.getLogger(VotoRepoImpleMongo.class);
    @Autowired
    private PautaMongoRepository pautaRepo;

    @Override
    public boolean existeVoto(String cpfDoAssociado, String idPauta) {

        logger.debug("Iniciado verificação de existencia de voto com cpf: " + cpfDoAssociado + " e pauta ID: " + idPauta);

        return pautaRepo.existsByIdAndVotosCpfDoAssociado(idPauta, cpfDoAssociado);
    }

    @Override
    public Voto registrarVoto(Voto voto) {

        logger.debug("Iniciado registro de voto.", voto);

        Optional<PautaEntity> pautaEntityOptional = pautaRepo.findById(voto.getIdDaPauta());

        if (pautaEntityOptional.isEmpty()) throw new PautaNaoEncotradaException();

        PautaEntity pautaEntity = pautaEntityOptional.get();

        pautaEntity.getVotos().add(voto);

        pautaRepo.save(pautaEntity);

        return voto;
    }

    @Override
    public List<Voto> listarPeloIdDaPauta(String idPauta) {

        logger.debug("Iniciado listagem de votos da pauta ID: " + idPauta);

        Optional<PautaEntity> pautaOptional = pautaRepo.findById(idPauta);

        if (pautaOptional.isEmpty()) return new ArrayList<>();

        return pautaOptional.get().getVotos();
    }
}
