package com.desafio.sessoesdevoto.infraestrutura.configuracoes;

import com.desafio.sessoesdevoto.dominio.portas.interfaces.PautaService;
import com.desafio.sessoesdevoto.dominio.portas.interfaces.PermissorDeVoto;
import com.desafio.sessoesdevoto.dominio.portas.interfaces.VotoService;
import com.desafio.sessoesdevoto.dominio.portas.repositorios.PautaRepositoryPort;
import com.desafio.sessoesdevoto.dominio.portas.repositorios.VotoRepositoryPort;
import com.desafio.sessoesdevoto.dominio.servicos.PautaServiceImple;
import com.desafio.sessoesdevoto.dominio.servicos.VotoServiceImple;
import com.desafio.sessoesdevoto.infraestrutura.repositorios.nosql.PautaRepoImpleMongo;
import com.desafio.sessoesdevoto.infraestrutura.repositorios.nosql.VotoRepoImpleMongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ConfiguracoesDeBeans {

    @Bean
    public PautaService pautaService(
            PautaRepositoryPort pautaRepositoryPort
    ) {

        return new PautaServiceImple(pautaRepositoryPort);
    }

    @Bean
    public PautaRepositoryPort pautaRepositoryPort() {

        return new PautaRepoImpleMongo();
    }

    @Bean
    public VotoService votoService(
            VotoRepositoryPort votoRepositoryPort,
            PautaRepositoryPort pautaRepositoryPort,
            PermissorDeVoto permissorDeVoto
    ) {

        return new VotoServiceImple(
                votoRepositoryPort,
                pautaRepositoryPort,
                permissorDeVoto,
                Clock.systemDefaultZone()
        );
    }

    @Bean
    public VotoRepositoryPort votoRepositoryPort() {

        return new VotoRepoImpleMongo();
    }

//    @Bean
//    public PermissorDeVoto permissorDeVoto(PermissorDeVotoImple permissaoDeVoto){
//
//        return permissaoDeVoto;
//    }

}
