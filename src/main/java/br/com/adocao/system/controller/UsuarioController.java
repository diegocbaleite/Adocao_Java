package br.com.adocao.system.controller;

import br.com.adocao.system.dto.UsuarioRequestDTO;
import br.com.adocao.system.dto.UsuarioResponseDTO;
import br.com.adocao.system.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // POST http://localhost:8080/api/usuarios
    @PostMapping
    public ResponseEntity<Map<String, Object>> criar(@Valid @RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO novo = usuarioService.criar(dto);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("mensagem", "Usuário cadastrado com sucesso!");
        resposta.put("usuario", novo);

        System.out.println(
                "Novo usuário cadastrado: "
                        + novo.nome()
                        + " (ID: "
                        + novo.id()
                        + ")"
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resposta);
    }

    // READ - BUSCAR POR ID
    // GET http://localhost:8080/api/usuarios/1
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscar(@PathVariable Long id) {

        UsuarioResponseDTO usuario = usuarioService.buscar(id);

        return ResponseEntity.ok(usuario);
    }

    // LISTAR
    // GET http://localhost:8080/api/usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<UsuarioResponseDTO> usuarios =
                usuarioService.listar(status, page, size);

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarios);
    }

    // UPDATE
    // PUT http://localhost:8080/api/usuarios/{id}
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequestDTO dto) {

        UsuarioResponseDTO atualizado =
                usuarioService.atualizar(id, dto);

        return ResponseEntity.ok(atualizado);
    }

    // DELETE
    // DELETE http://localhost:8080/api/usuarios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("mensagem", "Usuário excluído com sucesso!");
        resposta.put("status", 200);

        System.out.println(
                "Usuário com ID " + id + " foi excluído."
        );

        return ResponseEntity.ok(resposta);
    }
}