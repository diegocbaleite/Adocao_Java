package br.com.adocao.system.repository;

import br.com.adocao.system.model.Abrigo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {

    boolean existsByNome(String nome);

    Optional<Abrigo> findByNomeContainingIgnoreCase(String nome);
}
