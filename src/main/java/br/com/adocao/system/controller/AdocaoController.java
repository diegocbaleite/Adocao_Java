package br.com.adocao.system.controller;

import br.com.adocao.system.docs.AdocaoControllerDoc;
import br.com.adocao.system.model.Adocao;
import br.com.adocao.system.repository.AdocaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/adocoes")
public class AdocaoController implements AdocaoControllerDoc {

    private final AdocaoRepository adocaoRepository;

    // CREATE
    // POST http://localhost:8080/api/adocoes
    @Override
    @PostMapping
    public Adocao salvar(@RequestBody Adocao adocao) {
        return adocaoRepository.save(adocao);
    }

    // READ
    // GET http://localhost:8080/api/adocoes
    @Override
    @GetMapping
    public List<Adocao> listar() {
        return adocaoRepository.findAll();
    }

    // UPDATE
    // PUT http://localhost:8080/api/adocoes/{id}
    @Override
    @PutMapping("/{id}")
    public Adocao atualizar(
            @PathVariable Long id,
            @RequestBody Adocao atualizado) {

        return adocaoRepository.findById(id)
                .map(adocao -> {
                    adocao.setUsuario(atualizado.getUsuario());
                    adocao.setAnimal(atualizado.getAnimal());
                    adocao.setDataAdocao(atualizado.getDataAdocao());
                    adocao.setAprovado(atualizado.getAprovado());

                    return adocaoRepository.save(adocao);
                })
                .orElseThrow(() ->
                        new RuntimeException(
                                "Nenhuma adoção foi encontrada."
                        )
                );
    }

    // DELETE
    // DELETE http://localhost:8080/api/adocoes/{id}
    @Override
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        adocaoRepository.deleteById(id);
    }
}