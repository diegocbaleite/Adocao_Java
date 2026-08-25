package br.com.adocao.system.controller;

import br.com.adocao.system.dto.UsuarioRequestDTO;
import br.com.adocao.system.dto.UsuarioResponseDTO;
import br.com.adocao.system.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    /**
     * Cadastra um novo usuário no sistema.
     *
     * <p>Recebe os dados do usuário através de um {@link UsuarioRequestDTO},
     * realiza as validações definidas no DTO e delega a criação para a camada
     * de serviço.</p>
     *
     * <p>Em caso de sucesso, retorna os dados do usuário cadastrado juntamente
     * com uma mensagem de confirmação.</p>
     */
    @Operation(
            summary = "Cadastra um novo usuário",
            description = """
                    Endpoint responsável por cadastrar um novo usuário no sistema.
                    
                    O usuário deve ser enviado no corpo da requisição no formato JSON.
                    Os dados recebidos são validados através da anotação @Valid antes
                    de serem encaminhados para a camada de serviço.
                    
                    Em caso de sucesso, o sistema retorna:
                    - Uma mensagem informando que o cadastro foi realizado;
                    - Os dados do usuário cadastrado;
                    - O ID gerado para o usuário.
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuário cadastrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados da requisição inválidos"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Usuário já cadastrado"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor"
            )
    })
    @PostMapping
    public ResponseEntity<Map<String, Object>> criar(
            @Valid @RequestBody UsuarioRequestDTO dto) {

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

        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

        // READ - BUSCAR POR ID
        // GET http://localhost:8080/api/usuarios/1

    @Operation(
            summary = "Busca um usuário por ID",
            description = """
                    Endpoint responsável por buscar um usuário cadastrado no sistema
                    através do seu identificador único (ID).
                    
                    O ID deve ser informado como parâmetro na URL.
                    
                    Exemplo:
                    GET /api/usuarios/1
                    
                    Em caso de sucesso, retorna os dados do usuário encontrado.
                    Caso o usuário não exista, a camada de serviço deverá retornar
                    uma resposta indicando que o recurso não foi encontrado.
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuário encontrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "ID informado é inválido"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscar(
            @Parameter(
                    description = "ID único do usuário que será consultado",
                    example = "1",
                    required = true
            )
            @PathVariable Long id) {

        UsuarioResponseDTO usuario = usuarioService.buscar(id);

        return ResponseEntity.ok(usuario);
    }

    // LISTAR
// GET http://localhost:8080/api/usuarios

    @Operation(
            summary = "Lista os usuários",
            description = """
                Endpoint responsável por listar os usuários cadastrados no sistema.

                É possível utilizar o parâmetro 'status' para filtrar os usuários
                por seu status.

                A consulta também possui paginação através dos parâmetros 'page'
                e 'size'.

                Exemplos:

                GET /api/usuarios
                GET /api/usuarios?status=ATIVO
                GET /api/usuarios?page=0&size=10
                GET /api/usuarios?status=ATIVO&page=0&size=10

                Quando nenhum usuário for encontrado, o endpoint retorna HTTP 204
                (No Content).
                """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuários encontrados com sucesso"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Nenhum usuário encontrado"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Parâmetros de consulta inválidos"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor"
            )
    })
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar(

            @Parameter(
                    description = "Filtra os usuários pelo status",
                    example = "ATIVO",
                    required = false
            )
            @RequestParam(required = false) String status,

            @Parameter(
                    description = "Número da página. A primeira página é 0",
                    example = "0",
                    required = false
            )
            @RequestParam(defaultValue = "0") int page,

            @Parameter(
                    description = "Quantidade de usuários retornados por página",
                    example = "10",
                    required = false
            )
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

    @Operation(
            summary = "Atualiza um usuário",
            description = """
                Endpoint responsável por atualizar os dados de um usuário
                já cadastrado no sistema.

                O ID do usuário deve ser informado na URL e os novos dados
                devem ser enviados no corpo da requisição em formato JSON.

                Os dados recebidos são validados através da anotação @Valid.

                Exemplo:
                PUT /api/usuarios/1

                Em caso de sucesso, retorna os dados atualizados do usuário.
                """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuário atualizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados da requisição ou ID inválidos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Conflito ao atualizar o usuário"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(

            @Parameter(
                    description = "ID único do usuário que será atualizado",
                    example = "1",
                    required = true
            )
            @PathVariable Long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados atualizados do usuário",
                    required = true
            )
            @Valid @RequestBody UsuarioRequestDTO dto) {

        UsuarioResponseDTO atualizado =
                usuarioService.atualizar(id, dto);

        return ResponseEntity.ok(atualizado);
    }

    // DELETE
// DELETE http://localhost:8080/api/usuarios/{id}

    @Operation(
            summary = "Exclui um usuário",
            description = """
                Endpoint responsável por excluir um usuário cadastrado no sistema.

                O ID do usuário deve ser informado como parâmetro na URL.

                Exemplo:
                DELETE /api/usuarios/1

                Após a exclusão, o sistema retorna uma mensagem de confirmação.
                """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuário excluído com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "ID informado é inválido"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletar(

            @Parameter(
                    description = "ID único do usuário que será excluído",
                    example = "1",
                    required = true
            )
            @PathVariable Long id) {

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