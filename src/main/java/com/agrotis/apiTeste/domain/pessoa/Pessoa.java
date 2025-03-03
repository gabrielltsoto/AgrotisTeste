package com.agrotis.apiTeste.domain.pessoa;

import com.agrotis.apiTeste.domain.laboratorio.Laboratorio;
import com.agrotis.apiTeste.domain.laboratorio.LaboratorioRepository;
import com.agrotis.apiTeste.domain.propriedade.Propriedade;
import com.agrotis.apiTeste.domain.propriedade.PropriedadeRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Table(name = "pessoas")
@Entity(name="Pessoa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pessoa {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nome;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        private Instant dataInicial;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        private Instant  dataFinal;
        @ManyToOne
        @JoinColumn(name = "propriedade_id")
        private Propriedade propriedade;
        @ManyToOne
        @JoinColumn(name = "laboratorio_id")
        private Laboratorio laboratorio;
        private String observacoes;

        public Pessoa(DadosCadastroPessoa pessoa, Propriedade propriedade, Laboratorio laboratorio){
                this.nome = pessoa.nome();
                this.dataInicial = pessoa.dataInicial();
                this.dataFinal = pessoa.dataFinal();
                this.propriedade = propriedade;
                this.laboratorio = laboratorio;
                this.observacoes = pessoa.observacoes();
        }

        public void atualizarPessoa(DadosAtualizacaoPessoa dados, PropriedadeRepository propriedadeRepository, LaboratorioRepository laboratorioRepository){
            Optional.ofNullable(dados.nome()).ifPresent(this::setNome);
            Optional.ofNullable(dados.dataInicial()).ifPresent(this::setDataInicial);
            Optional.ofNullable(dados.dataFinal()).ifPresent(this::setDataFinal);
            Optional.ofNullable(dados.observacoes()).ifPresent(this::setObservacoes);

            // Atualiza a propriedade, se um novo ID for informado
            Optional.ofNullable(dados.propriedadeId()).ifPresent(id ->
                    this.setPropriedade(propriedadeRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Propriedade com ID " + id + " não encontrada")))
            );

            // Atualiza o laboratório, se um novo ID for informado
            Optional.ofNullable(dados.laboratorioId()).ifPresent(id ->
                    this.setLaboratorio(laboratorioRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Laboratório com ID " + id + " não encontrado")))
            );
        }
}