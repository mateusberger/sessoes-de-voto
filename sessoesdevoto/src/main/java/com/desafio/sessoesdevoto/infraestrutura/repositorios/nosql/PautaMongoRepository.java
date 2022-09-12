package com.desafio.sessoesdevoto.infraestrutura.repositorios.nosql;

import com.desafio.sessoesdevoto.infraestrutura.entidades.PautaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PautaMongoRepository extends MongoRepository<PautaEntity, String> {

    Boolean existsByIdAndVotosCpfDoAssociado(String idPauta, String cpdDoAssociado);

}