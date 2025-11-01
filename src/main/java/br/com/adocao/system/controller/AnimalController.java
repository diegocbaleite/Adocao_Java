package br.com.adocao.system.controller;

import br.com.adocao.system.model.Animal;
import br.com.adocao.system.repository.AnimalRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animais")
public class AnimalController {

    private final AnimalRepository repository;

    public AnimalController(AnimalRepository repository) {
        this.repository = repository;
    }

    // SALVAR metodo POST http://localhost:8080/api/animais
    @PostMapping
    public Animal salvar(@RequestBody Animal animal) {
        return repository.save(animal);
    }

    @GetMapping
    public List<Animal> listar() {
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public Animal atualizar(@PathVariable Long id, @RequestBody Animal atualizado) {
        return repository.findById(id)
                .map(animal -> {
                    animal.setNome(atualizado.getNome());
                    animal.setEspecie(atualizado.getEspecie());
                    animal.setRaca(atualizado.getRaca());
                    animal.setIdade(atualizado.getIdade());
                    animal.setDescricao(atualizado.getDescricao());
                    return repository.save(animal);
                })
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
