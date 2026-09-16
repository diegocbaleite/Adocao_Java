package br.com.adocao.system.docs;

import br.com.adocao.system.dto.AdocaoResponseDTO;
import br.com.adocao.system.dto.error.ApiErrorResponse;
import br.com.adocao.system.model.Adocao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(
        name = "Adoções",
        description = "Gerenciamento das adoções de animais"
)
public interface AdocaoControllerDoc {

    @Operation(
            summary = "Cadastrar uma adoção",
            description = "Registra uma nova adoção de animal no sistema."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Adoção cadastrada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados da adoção inválidos",
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
    Adocao salvar(Adocao adocao);


    @Operation(
            summary = "Listar adoções",
            description = "Retorna todas as adoções cadastradas no sistema."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Adoções encontradas com sucesso"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Nenhuma adoção encontrada"
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
    List<AdocaoResponseDTO> listar();


    @Operation(
            summary = "Atualizar adoção",
            description = "Atualiza os dados de uma adoção existente."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Adoção atualizada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Adoção não encontrada",
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
    Adocao atualizar(Long id, Adocao atualizado);


    @Operation(
            summary = "Remover adoção por ID",
            description = "Remove uma adoção cadastrada no sistema."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Adoção removida com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Adoção não encontrada",
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
    void deletar(Long id);
}