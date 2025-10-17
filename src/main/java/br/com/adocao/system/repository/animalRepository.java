package br.com.adocao.system.repository;


import br.com.adocao.system.model.animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface animalRepository extends JpaRepository<animal, Long> {
}
