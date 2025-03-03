package com.agrotis.apiTeste.controller;

import com.agrotis.apiTeste.domain.laboratorio.Laboratorio;
import com.agrotis.apiTeste.domain.laboratorio.LaboratorioRepository;
import com.agrotis.apiTeste.domain.pessoa.*;
import com.agrotis.apiTeste.domain.propriedade.Propriedade;
import com.agrotis.apiTeste.domain.propriedade.PropriedadeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private PropriedadeRepository propriedadeRepository;
    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @GetMapping
    public ResponseEntity<Page<DadosListagemPessoa>> listar(@PageableDefault(size = 20, sort = {"nome"}) Pageable pageable){
        Page<DadosListagemPessoa> page = pessoaRepository.findAll(pageable).map(DadosListagemPessoa::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPessoa dados, UriComponentsBuilder builder){
        Propriedade propriedade = propriedadeRepository.findById(dados.propriedadeId())
                .orElseThrow(() -> new RuntimeException("Propriedade não encontrada"));

        Laboratorio laboratorio = laboratorioRepository.findById(dados.laboratorioId())
                .orElseThrow(() -> new RuntimeException("Laboratório não encontrado"));

        Pessoa pessoa = new Pessoa(dados, propriedade, laboratorio);
        pessoaRepository.save(pessoa);
        var uri = builder.path("/pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosListagemPessoa(pessoa));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        Pessoa pessoa = pessoaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemPessoa(pessoa));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizacaoPessoa dadosAtualizacaoPessoa){
        Pessoa pessoa = pessoaRepository.getReferenceById(dadosAtualizacaoPessoa.id());
        pessoa.atualizarPessoa(dadosAtualizacaoPessoa, propriedadeRepository, laboratorioRepository);
        return ResponseEntity.ok(new DadosListagemPessoa(pessoa));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        pessoaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
