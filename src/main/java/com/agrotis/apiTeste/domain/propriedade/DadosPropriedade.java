package com.agrotis.apiTeste.domain.propriedade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosPropriedade(
    @NotBlank(message = "O nome da propriedade é obrigatório")
    String nome
){}
