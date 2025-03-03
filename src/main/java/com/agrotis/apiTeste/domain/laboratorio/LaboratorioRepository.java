package com.agrotis.apiTeste.domain.laboratorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {
    @Query("SELECT l.id, l.nome, COUNT(p) " +
            "FROM Laboratorio l " +
            "LEFT JOIN Pessoa p ON l.id = p.laboratorio.id " +
            "GROUP BY l.id, l.nome " +
            "HAVING COUNT(p) >= :minPessoas")
    Page<Object[]> listarLaboratoriosComQuantidade(@Param("minPessoas") Long minPessoas, Pageable pageable);

}
