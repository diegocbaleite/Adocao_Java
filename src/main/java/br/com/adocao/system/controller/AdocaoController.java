package br.com.adocao.system.controller;

import br.com.adocao.system.docs.AdocaoControllerDoc;
import br.com.adocao.system.dto.AdocaoRequestDTO;
import br.com.adocao.system.dto.AdocaoResponseDTO;
import br.com.adocao.system.service.AdocaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/adocoes")
public class AdocaoController implements AdocaoControllerDoc {

    private final AdocaoService adocaoService;

    @Override
    @PostMapping
    public AdocaoResponseDTO salvar(@Valid @RequestBody AdocaoRequestDTO dto) {
        return adocaoService.criar(dto);
    }

    @Override
    @GetMapping
    public List<AdocaoResponseDTO> listar() {
        return adocaoService.listar();
    }

    @Override
    @GetMapping("/{id}")
    public AdocaoResponseDTO buscar(@PathVariable Long id) {
        return adocaoService.buscar(id);
    }

    @Override
    @PutMapping("/{id}")
    public AdocaoResponseDTO atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AdocaoRequestDTO dto) {
        return adocaoService.atualizar(id, dto);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        adocaoService.deletar(id);
    }

    @Override
    @PatchMapping("/{id}/aprovar")
    public AdocaoResponseDTO aprovar(@PathVariable Long id) {
        return adocaoService.aprovar(id);
    }
}
