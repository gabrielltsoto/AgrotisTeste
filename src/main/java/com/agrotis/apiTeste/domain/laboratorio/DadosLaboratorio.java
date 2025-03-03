package com.agrotis.apiTeste.domain.laboratorio;

import jakarta.validation.constraints.NotBlank;

public record DadosLaboratorio(
        @NotBlank
        String nome
) {}
