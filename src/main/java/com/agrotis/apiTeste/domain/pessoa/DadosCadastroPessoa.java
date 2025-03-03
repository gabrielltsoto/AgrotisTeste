package com.agrotis.apiTeste.domain.pessoa;

import com.agrotis.apiTeste.domain.laboratorio.DadosLaboratorio;
import com.agrotis.apiTeste.domain.propriedade.DadosPropriedade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record DadosCadastroPessoa(
        @NotBlank
        String nome,
        @NotNull
        Instant dataInicial,
        @NotNull
        Instant dataFinal,
        @NotNull
        Long propriedadeId,
        @NotNull
        Long laboratorioId,
        String observacoes
) {}
