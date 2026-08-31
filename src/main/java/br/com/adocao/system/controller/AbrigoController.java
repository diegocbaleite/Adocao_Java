package br.com.adocao.system.controller;

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
public class AbrigoController {

    private final AbrigoService abrigoService;

    // CREATE
    @PostMapping
    public ResponseEntity<AbrigoResponseDTO> criar(
            @Valid @RequestBody AbrigoRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(abrigoService.criar(dto));
    }

    // LISTAR
    @GetMapping
    public ResponseEntity<List<AbrigoResponseDTO>> listar() {

        List<AbrigoResponseDTO> abrigos = abrigoService.listar();

        if (abrigos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(abrigos);
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<AbrigoResponseDTO> buscar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                abrigoService.buscar(id)
        );
    }

    // BUSCAR POR NOME
    @GetMapping("/nome")
    public ResponseEntity<List<AbrigoResponseDTO>> buscarPorNome(
            @RequestParam String nome) {

        List<AbrigoResponseDTO> abrigos =
                abrigoService.buscarPorNome(nome);

        if (abrigos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(abrigos);
    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<AbrigoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AbrigoRequestDTO dto) {

        return ResponseEntity.ok(
                abrigoService.atualizar(id, dto)
        );
    }

    // EXCLUIR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {

        abrigoService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}