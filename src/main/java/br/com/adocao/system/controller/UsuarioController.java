package br.com.adocao.system.controller;

import br.com.adocao.system.dto.UsuarioDTO;
import br.com.adocao.system.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
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

    // CREATE metodo POST http://localhost:8080/api/usuarios
    @PostMapping
    public ResponseEntity<Map<String, Object>> criar(@Valid @RequestBody UsuarioDTO dto) {
        UsuarioDTO novo = usuarioService.criar(dto);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("mensagem", "Usuário cadastrado com sucesso!");
        resposta.put("usuario", novo);

        System.out.println("Novo usuário cadastrado: " + novo.getNome() + " (ID: " + novo.getId() + ")");

        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }


    // READ (by ID) metodo GET http://localhost:8080/api/usuarios/1
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscar(@PathVariable Long id) {
        UsuarioDTO usuario = usuarioService.buscar(id); // já lança ResponseStatusException se não achar
        return ResponseEntity.ok(usuario);
    }

    // LIST metodo GET http://localhost:8080/api/usuarios
    @SneakyThrows
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) throws InterruptedException {
        List<UsuarioDTO> usuarios = usuarioService.listar(status, page, size);
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    // UPDATE PUT http://localhost:8080/api/usuarios/{id}
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioDTO dto
    ) {
        UsuarioDTO atualizado = usuarioService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("mensagem", "Usuário excluído com sucesso!");
        resposta.put("status", 200);

        System.out.println("Usuário com ID " + id + " foi excluído.");

        return ResponseEntity.ok(resposta);
    }

}
