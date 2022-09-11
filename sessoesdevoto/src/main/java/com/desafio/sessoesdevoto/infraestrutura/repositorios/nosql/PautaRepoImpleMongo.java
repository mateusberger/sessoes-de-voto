package com.desafio.sessoesdevoto.infraestrutura.repositorios.nosql;

import com.desafio.sessoesdevoto.dominio.Pauta;
import com.desafio.sessoesdevoto.dominio.excecoes.PautaNaoEncotradaException;
import com.desafio.sessoesdevoto.dominio.portas.repositorios.PautaRepositoryPort;
import com.desafio.sessoesdevoto.infraestrutura.entidades.PautaEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PautaRepoImpleMongo implements PautaRepositoryPort {

    private static final Logger logger = LoggerFactory.getLogger(PautaRepoImpleMongo.class);

    @Autowired
    private PautaMongoRepository pautaRepo;

    @Override
    public Pauta registrarPauta(Pauta pauta) {

        logger.debug("Iniciado registro de pauta. ", pauta);

        return pautaRepo.save(new PautaEntity(pauta)).toPauta();
    }

    @Override
    public Optional<Pauta> buscarPeloId(String idPauta) {

        logger.debug("Iniciado busca de pauta ID: " + idPauta);

        return pautaRepo.findById(idPauta).map(PautaEntity::toPauta);
    }

    @Override
    public Pauta alterarPauta(Pauta pauta) {

        logger.debug("Iniciado alteração de pauta: ", pauta);

        Optional<PautaEntity> pautaOptional = pautaRepo.findById(pauta.getId());

        if (pautaOptional.isEmpty()) throw new PautaNaoEncotradaException();

        PautaEntity pautaEntity = pautaOptional.get();
        pautaEntity.atualizar(pauta);

        return pautaRepo.save(pautaEntity).toPauta();
    }

    @Override
    public List<Pauta> listarTodas() {

        logger.debug("Iniciando listagem de pautas");

        return pautaRepo.findAll().stream().map(PautaEntity::toPauta).toList();
    }
}
