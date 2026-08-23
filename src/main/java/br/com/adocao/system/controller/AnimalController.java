package br.com.adocao.system.controller;

import br.com.adocao.system.model.Animal;
import br.com.adocao.system.repository.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/animais")
public class AnimalController {

    private final AnimalRepository animalRepository;

    // CREATE
    // SALVAR metodo POST http://localhost:8080/api/animais
    @PostMapping
    public Animal salvar(@RequestBody Animal animal) {
        return animalRepository.save(animal);
    }

    @GetMapping
    public List<Animal> listar() {
        return animalRepository.findAll();
    }

    @PutMapping("/{id}")
    public Animal atualizar(@PathVariable Long id, @RequestBody Animal atualizado) {
        return animalRepository.findById(id)
                .map(animal -> {
                    animal.setNome(atualizado.getNome());
                    animal.setEspecie(atualizado.getEspecie());
                    animal.setRaca(atualizado.getRaca());
                    animal.setIdade(atualizado.getIdade());
                    animal.setDescricao(atualizado.getDescricao());
                    return animalRepository.save(animal);
                })
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        animalRepository.deleteById(id);
    }
}
