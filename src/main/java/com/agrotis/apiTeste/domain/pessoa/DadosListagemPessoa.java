package com.agrotis.apiTeste.domain.pessoa;

import com.agrotis.apiTeste.domain.laboratorio.Laboratorio;
import com.agrotis.apiTeste.domain.propriedade.Propriedade;
import jakarta.persistence.Embedded;

import java.time.Instant;

public record DadosListagemPessoa(Long id, String nome, Instant dataInicial, Instant dataFinal, Propriedade propriedade, Laboratorio laboratorio, String observacoes) {
    public DadosListagemPessoa(Pessoa pessoa){
        this(pessoa.getId(), pessoa.getNome(), pessoa.getDataInicial(), pessoa.getDataFinal(), pessoa.getPropriedade(), pessoa.getLaboratorio(), pessoa.getObservacoes());
    }
}
