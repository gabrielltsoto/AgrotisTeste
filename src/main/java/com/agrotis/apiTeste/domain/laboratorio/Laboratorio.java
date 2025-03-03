package com.agrotis.apiTeste.domain.laboratorio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Table(name = "laboratorios")
@Entity(name = "Laboratorio")
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Laboratorio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Laboratorio(DadosLaboratorio laboratorio){
        this.nome = laboratorio.nome();
    }

    public Laboratorio(Long id){
        this.id = id;
    }
}
