package br.com.adocao.system.controller;

import br.com.adocao.system.docs.UsuarioControllerDoc;
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
public class UsuarioController implements UsuarioControllerDoc {

    @Autowired
    private UsuarioService usuarioService;

    // CREATE
    // POST http://localhost:8080/api/usuarios
    @Override
    @PostMapping
    public ResponseEntity<Map<String, Object>> criar(
            @Valid @RequestBody UsuarioRequestDTO dto) {

        UsuarioResponseDTO novo = usuarioService.criar(dto);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("mensagem", "Usuário cadastrado com sucesso!");
        resposta.put("usuario", novo);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resposta);
    }

    // BUSCAR POR ID
    // GET http://localhost:8080/api/usuarios/1
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                usuarioService.buscar(id)
        );
    }


    // BUSCAR POR NOME  //GET http://localhost:8080/api/usuarios/nome?nome=
    @Override
    @GetMapping("/nome")
    public ResponseEntity<List<UsuarioResponseDTO>> buscarPorNome(
            @RequestParam String nome) {

        List<UsuarioResponseDTO> usuarios = usuarioService.buscarPorNome(nome);

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarios);
    }

    // LISTAR GET http://localhost:8080/api/usuarios
    @Override
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<UsuarioResponseDTO> usuarios = usuarioService.listar(status, page, size);

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarios);
    }

    // PUT http://localhost:8080/api/usuarios/{id}
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequestDTO dto) {

        return ResponseEntity.ok(usuarioService.atualizar(id, dto)
        );
    }

    // DELETE http://localhost:8080/api/usuarios/{id}
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletar(
            @PathVariable Long id) {

        usuarioService.deletar(id);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("mensagem", "Usuário excluído com sucesso!");
        resposta.put("status", 200);

        return ResponseEntity.ok(resposta);
    }
}