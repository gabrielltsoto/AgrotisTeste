package com.agrotis.apiTeste.domain.propriedade;

import com.agrotis.apiTeste.domain.propriedade.Propriedade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropriedadeRepository extends JpaRepository<Propriedade, Long> {
}