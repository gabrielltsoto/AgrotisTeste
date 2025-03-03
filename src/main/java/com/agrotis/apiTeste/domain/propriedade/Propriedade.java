package com.agrotis.apiTeste.domain.propriedade;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Table(name = "propriedades")
@Entity(name = "Propriedade")
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Propriedade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Propriedade(DadosPropriedade propriedade){
        this.nome = propriedade.nome();
    }

    public Propriedade(Long id){
        this.id = id;
    }
}
