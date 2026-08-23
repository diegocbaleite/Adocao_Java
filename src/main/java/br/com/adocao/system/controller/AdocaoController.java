package br.com.adocao.system.controller;

import br.com.adocao.system.model.Adocao;
import br.com.adocao.system.repository.AdocaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/adocoes")
public class AdocaoController {

    private final AdocaoRepository adocaoRepository;

    // CREATE
    // SALVAR metodo POST http://localhost:8080/api/adocoes
    @PostMapping
    public Adocao salvar(@RequestBody Adocao adocao) {
        return adocaoRepository.save(adocao);
    }

    @GetMapping
    public List<Adocao> listar() {
        return adocaoRepository.findAll();
    }

    @PutMapping("/{id}")
    public Adocao atualizar(@PathVariable Long id, @RequestBody Adocao atualizado) {
        return adocaoRepository.findById(id)
                .map(adocao -> {
                    adocao.setUsuario(atualizado.getUsuario());
                    adocao.setAnimal(atualizado.getAnimal());
                    adocao.setDataAdocao(atualizado.getDataAdocao());
                    adocao.setAprovado(atualizado.getAprovado());
                    return adocaoRepository.save(adocao);
                })
                .orElseThrow(() -> new RuntimeException("Nenhum adoção foi encontrado."));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        adocaoRepository.deleteById(id);
    }
}
