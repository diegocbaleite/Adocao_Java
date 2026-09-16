package br.com.adocao.system.controller;

import br.com.adocao.system.docs.AbrigoControllerDoc;
import br.com.adocao.system.dto.AbrigoRequestDTO;
import br.com.adocao.system.dto.AbrigoResponseDTO;
import br.com.adocao.system.service.AbrigoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/abrigos")
public class AbrigoController implements AbrigoControllerDoc {

    private final AbrigoService abrigoService;

    // CREATE
    @Override
    @PostMapping
    public AbrigoResponseDTO criar(
            @Valid @RequestBody AbrigoRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(abrigoService.criar(dto)).getBody();
    }

    // LISTAR
    @Override
    @GetMapping
    public List<AbrigoResponseDTO> listar() {

        List<AbrigoResponseDTO> abrigos =
                abrigoService.listar();

        if (abrigos.isEmpty()) {
            return (List<AbrigoResponseDTO>) ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(abrigos).getBody();
    }

    // BUSCAR POR ID
    @Override
    @GetMapping("/{id}")
    public AbrigoResponseDTO buscar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                abrigoService.buscar(id)
        ).getBody();
    }

    // BUSCAR POR NOME
    @Override
    @GetMapping("/nome")
    public List<AbrigoResponseDTO> buscarPorNome(
            @RequestParam String nome) {

        List<AbrigoResponseDTO> abrigos =
                abrigoService.buscarPorNome(nome);

        if (abrigos.isEmpty()) {
            return (List<AbrigoResponseDTO>) ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(abrigos).getBody();
    }

    // ATUALIZAR
    @Override
    @PutMapping("/{id}")
    public AbrigoResponseDTO atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AbrigoRequestDTO dto) {

        return ResponseEntity.ok(abrigoService.atualizar(id, dto)
        ).getBody();
    }

    // EXCLUIR
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(
            @PathVariable Long id) {

        abrigoService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}