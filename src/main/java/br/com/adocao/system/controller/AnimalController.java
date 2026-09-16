package br.com.adocao.system.controller;

import br.com.adocao.system.docs.AnimalControllerDoc;
import br.com.adocao.system.dto.AnimalRequestDTO;
import br.com.adocao.system.dto.AnimalResponseDTO;
import br.com.adocao.system.service.AnimalService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/animais")
@CrossOrigin(origins = "http://localhost:5173")
public class AnimalController implements AnimalControllerDoc {

    private final AnimalService animalService;


    // POST /api/animais
    @Override
    @PostMapping
    public ResponseEntity<AnimalResponseDTO> salvar(
            @Valid @RequestBody AnimalRequestDTO dto) {

        AnimalResponseDTO animal = animalService.criar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(animal);
    }

    // READ
    // GET /api/animais
    @Override
    @GetMapping
    public ResponseEntity<List<AnimalResponseDTO>> listar(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<AnimalResponseDTO> animais =
                animalService.listar(status, page, size);

        if (animais.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(animais);
    }

    // UPDATE
    // PUT /api/animais/{id}
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AnimalRequestDTO dto) {

        AnimalResponseDTO animal =
                animalService.atualizar(id, dto);

        return ResponseEntity.ok(animal);
    }

    // DELETE
    // DELETE /api/animais/{id}
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {

        animalService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}