package br.com.adocao.system.docs;

import br.com.adocao.system.dto.UsuarioRequestDTO;
import br.com.adocao.system.dto.UsuarioResponseDTO;
import br.com.adocao.system.dto.error.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@Tag(
        name = "Usuários",
        description = "Gerenciamento dos usuários do sistema de adoção de animais"
)
public interface UsuarioControllerDoc {

    @Operation(
            summary = "Cadastrar usuário",
            description = "Cadastra um novo usuário no sistema."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuário cadastrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiErrorResponse.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "CPF ou e-mail já cadastrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiErrorResponse.class
                            )
                    )
            )
    })
    ResponseEntity<Map<String, Object>> criar(
            UsuarioRequestDTO dto
    );


    @Operation(
            summary = "Buscar usuário por ID",
            description = "Retorna os dados de um usuário existente."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuário encontrado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiErrorResponse.class
                            )
                    )
            )
    })
    ResponseEntity<UsuarioResponseDTO> buscar(Long id);


    @Operation(
            summary = "Buscar usuário por nome",
            description = """
                    Retorna os usuários que possuem o nome informado.

                    A busca não diferencia letras maiúsculas e minúsculas
                    e permite pesquisar parte do nome.

                    Exemplo:
                    GET /api/usuarios/nome?nome=Diego
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
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiErrorResponse.class
                            )
                    )
            )
    })
    ResponseEntity<List<UsuarioResponseDTO>> buscarPorNome(
            String nome
    );


    @Operation(
            summary = "Listar usuários",
            description = """
                    Retorna os usuários cadastrados com paginação
                    e filtro por status.

                    Exemplos:

                    GET /api/usuarios

                    GET /api/usuarios?status=ATIVO

                    GET /api/usuarios?page=0&size=10
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuários encontrados"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Nenhum usuário encontrado"
            )
    })
    ResponseEntity<List<UsuarioResponseDTO>> listar(
            String status,
            int page,
            int size
    );


    @Operation(
            summary = "Atualizar usuário",
            description = "Atualiza os dados de um usuário existente."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuário atualizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiErrorResponse.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Conflito ao atualizar usuário",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiErrorResponse.class
                            )
                    )
            )
    })
    ResponseEntity<UsuarioResponseDTO> atualizar(
            Long id,
            UsuarioRequestDTO dto
    );


    @Operation(
            summary = "Remover usuário por ID",
            description = "Remove um usuário cadastrado no sistema."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuário removido com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiErrorResponse.class
                            )
                    )
            )
    })
    ResponseEntity<Map<String, Object>> deletar(Long id);
}