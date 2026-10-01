package br.com.adocao.system.docs;

import br.com.adocao.system.dto.AbrigoRequestDTO;
import br.com.adocao.system.dto.AbrigoResponseDTO;
import br.com.adocao.system.dto.error.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name = "Abrigos",
        description = "Gerenciamento dos abrigos de animais"
)
public interface AbrigoControllerDoc {

    @Operation(
            summary = "Cadastrar um abrigo",
            description = "Registra um novo abrigo no sistema."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Abrigo cadastrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados do abrigo inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiErrorResponse.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Abrigo já cadastrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiErrorResponse.class
                            )
                    )
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
    AbrigoResponseDTO criar(AbrigoRequestDTO dto);


    @Operation(
            summary = "Listar abrigos",
            description = "Retorna todos os abrigos cadastrados no sistema."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Abrigos encontrados com sucesso"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Nenhum abrigo encontrado"
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
    List<AbrigoResponseDTO> listar();


    @Operation(
            summary = "Buscar abrigo por ID",
            description = "Retorna os dados de um abrigo existente."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Abrigo encontrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Abrigo não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiErrorResponse.class
                            )
                    )
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
    AbrigoResponseDTO buscar(Long id);


    @Operation(
            summary = "Buscar abrigo por nome",
            description = """
                    Retorna os abrigos que possuem o nome informado.

                    A busca não diferencia letras maiúsculas e minúsculas.
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Abrigos encontrados com sucesso"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Nenhum abrigo encontrado"
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
    List<AbrigoResponseDTO> buscarPorNome(String nome);


    @Operation(
            summary = "Atualizar abrigo",
            description = "Atualiza os dados de um abrigo existente."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Abrigo atualizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados do abrigo inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiErrorResponse.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Abrigo não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiErrorResponse.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Abrigo já cadastrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiErrorResponse.class
                            )
                    )
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
    AbrigoResponseDTO atualizar(Long id, AbrigoRequestDTO dto);


    @Operation(
            summary = "Remover abrigo por ID",
            description = "Remove um abrigo cadastrado no sistema."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Abrigo removido com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Abrigo não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiErrorResponse.class
                            )
                    )
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
    ResponseEntity<Object> deletar(Long id);
}