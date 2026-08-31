package br.com.adocao.system.controller;

import br.com.adocao.system.docs.AnimalControllerDoc;
import br.com.adocao.system.model.Animal;
import br.com.adocao.system.repository.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/animais")
@CrossOrigin(origins = "http://localhost:5173")
public class AnimalController implements AnimalControllerDoc {

    private final AnimalRepository animalRepository;

    // CREATE
    // POST http://localhost:8080/api/animais
    @Override
    @PostMapping
    public Animal salvar(@RequestBody Animal animal) {
        return animalRepository.save(animal);
    }

    // READ
    // GET http://localhost:8080/api/animais
    @Override
    @GetMapping
    public List<Animal> listar() {
        return animalRepository.findAll();
    }

    // UPDATE
    // PUT http://localhost:8080/api/animais/{id}
    @Override
    @PutMapping("/{id}")
    public Animal atualizar(
            @PathVariable Long id,
            @RequestBody Animal atualizado) {

        return animalRepository.findById(id)
                .map(animal -> {
                    animal.setNome(atualizado.getNome());
                    animal.setEspecie(atualizado.getEspecie());
                    animal.setRaca(atualizado.getRaca());
                    animal.setIdade(atualizado.getIdade());
                    animal.setDescricao(atualizado.getDescricao());
                    animal.setFotoUrl(atualizado.getFotoUrl());
                    animal.setStatus(atualizado.getStatus());
                    animal.setAbrigo(atualizado.getAbrigo());

                    return animalRepository.save(animal);
                })
                .orElseThrow(() ->
                        new RuntimeException("Animal não encontrado"));
    }

    // DELETE
    // DELETE http://localhost:8080/api/animais/{id}
    @Override
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        animalRepository.deleteById(id);
    }
}