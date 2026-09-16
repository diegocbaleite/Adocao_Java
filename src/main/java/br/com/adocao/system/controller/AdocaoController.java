package br.com.adocao.system.controller;

import br.com.adocao.system.docs.AdocaoControllerDoc;
import br.com.adocao.system.dto.AdocaoResponseDTO;
import br.com.adocao.system.model.Adocao;
import br.com.adocao.system.repository.AdocaoRepository;
import br.com.adocao.system.service.AdocaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/adocoes")
public class AdocaoController implements AdocaoControllerDoc {

    private final AdocaoRepository adocaoRepository;
    private final AdocaoService adocaoService;

    // POST http://localhost:8080/api/adocoes
    @Override
    @PostMapping
    public Adocao salvar(@RequestBody Adocao adocao) {
        return adocaoRepository.save(adocao);
    }

    // GET http://localhost:8080/api/adocoes
    @Override
    @GetMapping
    public List<Adocao> listar() {
        return adocaoRepository.findAll();
    }

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

    // DELETE http://localhost:8080/api/adocoes/{id}
    @Override
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        adocaoRepository.deleteById(id);
    }

    @PatchMapping("/{id}/aprovar")
    public ResponseEntity<AdocaoResponseDTO> aprovar(
            @PathVariable Long id) {

        AdocaoResponseDTO resposta = adocaoService.aprovar(id);

        return ResponseEntity.ok(resposta);
    }
}