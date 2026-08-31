package br.com.adocao.system.controller;

import br.com.adocao.system.model.Animal;
import br.com.adocao.system.repository.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/animais")
@CrossOrigin(origins = "http://localhost:5173")
public class AnimalController {

    private final AnimalRepository animalRepository;

    // POST /api/animais
    @PostMapping
    public Animal salvar(@RequestBody Animal animal) {
        return animalRepository.save(animal);
    }

    // GET /api/animais
    @GetMapping
    public List<Animal> listar() {
        return animalRepository.findAll();
    }

    // PUT /api/animais/{id}
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

    // DELETE /api/animais/{id}
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        animalRepository.deleteById(id);
    }
}