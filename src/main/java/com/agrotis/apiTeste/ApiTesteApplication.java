package com.agrotis.apiTeste;

import com.agrotis.apiTeste.domain.laboratorio.Laboratorio;
import com.agrotis.apiTeste.domain.laboratorio.LaboratorioRepository;
import com.agrotis.apiTeste.domain.pessoa.DadosCadastroPessoa;
import com.agrotis.apiTeste.domain.pessoa.Pessoa;
import com.agrotis.apiTeste.domain.pessoa.PessoaRepository;
import com.agrotis.apiTeste.domain.propriedade.DadosPropriedade;
import com.agrotis.apiTeste.domain.propriedade.Propriedade;
import com.agrotis.apiTeste.domain.propriedade.PropriedadeRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.github.javafaker.Faker;

import java.time.Instant;
import java.util.Random;

@SpringBootApplication
public class ApiTesteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTesteApplication.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner runner(PessoaRepository pessoaRepository, PropriedadeRepository propriedadeRepository, LaboratorioRepository laboratorioRepository) {
		return args -> {
			Faker faker = new Faker();
			Random random = new Random();

			for (int i = 0; i < 5; i++) {
				Propriedade propriedade = new Propriedade(null, "Propriedade " + faker.company().name());
				propriedadeRepository.save(propriedade);

				Laboratorio laboratorio = new Laboratorio(null, "Laboratório " + faker.company().name());
				laboratorioRepository.save(laboratorio);
			}
		};
	}

}
