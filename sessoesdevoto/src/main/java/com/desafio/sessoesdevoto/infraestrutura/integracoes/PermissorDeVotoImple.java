package com.desafio.sessoesdevoto.infraestrutura.integracoes;

import com.desafio.sessoesdevoto.dominio.excecoes.FalhaAoValidarPermissaoDeVotoException;
import com.desafio.sessoesdevoto.dominio.portas.interfaces.PermissorDeVoto;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Objects;

@Component("permissorDeVoto")
@FeignClient(value = "permissor-de-votos",
        url = "${dominio.permissordevoto.url}")
public interface PermissorDeVotoImple extends PermissorDeVoto {

    @GetMapping("/users/{cpfDoAssociado}")
    HashMap<String, String> buscaPermissaoDeVoto(@PathVariable String cpfDoAssociado);

    @Override
    default boolean permiteVoto(String cpfDoAssociado){
        try {

            HashMap<String, String> retorno = buscaPermissaoDeVoto(cpfDoAssociado);

            if (retorno.isEmpty()) throw new FalhaAoValidarPermissaoDeVotoException();

            if (!retorno.containsKey("status")) throw new FalhaAoValidarPermissaoDeVotoException();

            return (Objects.equals(retorno.get("status").trim().toUpperCase(), "ABLE_TO_VOTE"));

        } catch (Exception ex) {

            LoggerFactory.getLogger(PermissorDeVotoImple.class).error("Falha inesperada ao requisitar permissão de voto do cpf do associado: " + cpfDoAssociado, ex);

            throw new FalhaAoValidarPermissaoDeVotoException();
        }
    }
}
