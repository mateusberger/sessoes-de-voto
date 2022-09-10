package com.desafio.sessoesdevoto.dominio.dto;

import com.desafio.sessoesdevoto.dominio.Voto;

public record RegistrarVotoForm(

        String idPauta,
        String cpfDoAssociado,
        boolean voto
) {
    public RegistrarVotoForm {
        if (idPauta == null) throw new IllegalArgumentException("Id da pauta não foi informado");
        if (idPauta.isBlank()) throw new IllegalArgumentException("Id da pauta não pode ser vazio");
        if (cpfDoAssociado == null) throw new IllegalArgumentException("CPF do Associado não foi informado");
        if (cpfDoAssociado.isBlank()) throw new IllegalArgumentException("CPF do Associado não pode ser vazio");
        if (!cpfDoAssociado.matches("^\\d+$")) throw new IllegalArgumentException("CPF do Associado deve conter apenas números");
    }

    public Voto toVoto() {
        return new Voto(this.cpfDoAssociado, this.voto, this.idPauta);
    }
}
