package br.com.adocao.system.repository;

import br.com.adocao.system.model.Adocao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> {
}
