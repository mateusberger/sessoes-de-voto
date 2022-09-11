package com.desafio.sessoesdevoto.dominio.dto;

import com.desafio.sessoesdevoto.dominio.Pauta;

/**
 * Formulário de registro de uma Pauta
 *
 * @param nome Nome da Pauta (Obrigátorio e não pode estar em branco)
 * @param descricao Descrição da Pauta (Obrigátorio e não pode estar em branco)
 */
public record RegistrarPautaForm(
        String nome,
        String descricao
) {

    public RegistrarPautaForm {
        if (nome == null) throw new IllegalArgumentException("Nome da pauta não pode ser nulo");
        if (nome.isBlank()) throw new IllegalArgumentException("Nome da pauta não pode ser vazio");
        if (descricao == null) throw new IllegalArgumentException("Descrição da pauta não pode ser nulo");
        if (descricao.isBlank()) throw new IllegalArgumentException("Descrição da pauta não pode ser vazia");
    }

    /**
     * Converte o formulário em uma Pauta
     *
     * @return Pauta contendo o nome e descrição
     */
    public Pauta toPauta() {
        Pauta pauta = new Pauta();
        pauta.setNome(nome());
        pauta.setDescricao(descricao());
        return pauta;
    }
}
