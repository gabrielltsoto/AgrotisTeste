package com.agrotis.apiTeste.domain.pessoa;

import com.agrotis.apiTeste.domain.laboratorio.DadosLaboratorio;
import com.agrotis.apiTeste.domain.propriedade.DadosPropriedade;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;

public record DadosAtualizacaoPessoa(@NotNull Long id, String nome, Instant dataInicial, Instant dataFinal, Long propriedadeId, Long laboratorioId, String observacoes) {
}
