package com.agrotis.apiTeste.controller;

import com.agrotis.apiTeste.domain.laboratorio.DadosResumoLaboratorio;
import com.agrotis.apiTeste.domain.laboratorio.Laboratorio;
import com.agrotis.apiTeste.domain.laboratorio.LaboratorioRepository;
import com.agrotis.apiTeste.domain.pessoa.DadosListagemPessoa;
import com.agrotis.apiTeste.domain.pessoa.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("laboratorios")
public class LaboratorioController {
    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @GetMapping
    public ResponseEntity<Page<DadosResumoLaboratorio>> listarLaboratorios(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "0") Long minPessoas) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Object[]> resultados = laboratorioRepository.listarLaboratoriosComQuantidade(minPessoas, pageable);

        List<DadosResumoLaboratorio> laboratorios = resultados.stream()
                .map(r -> new DadosResumoLaboratorio((Long) r[0], (String) r[1], (Long) r[2]))
                .collect(Collectors.toList());

        laboratorios.sort(Comparator.comparingLong(DadosResumoLaboratorio::quantidadePessoas).reversed());

        return ResponseEntity.ok(new PageImpl<>(laboratorios, pageable, resultados.getTotalElements()));
    }
}
