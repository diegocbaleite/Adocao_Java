package br.com.adocao.system.repository;

import br.com.adocao.system.model.adocao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface adocaoRepository extends JpaRepository<adocao, Long> {
}
