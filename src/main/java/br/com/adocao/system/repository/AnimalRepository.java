package br.com.adocao.system.repository;

import br.com.adocao.system.model.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Page<Animal> findByStatus(String status, Pageable pageable);
}
