package br.com.adocao.system.controller;

import br.com.adocao.system.model.animal;
import br.com.adocao.system.repository.animalRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animais")
public class animalController {

    private final animalRepository repository;

    public animalController(animalRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public animal salvar(@RequestBody animal animal) {
        return repository.save(animal);
    }

    @GetMapping
    public List<animal> listar() {
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public animal atualizar(@PathVariable Long id, @RequestBody animal atualizado) {
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
