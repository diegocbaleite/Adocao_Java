package br.com.adocao.system.docs;

import br.com.adocao.system.dto.error.ApiErrorResponse;
import br.com.adocao.system.model.Animal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(
        name = "Animais",
        description = "Gerenciamento dos animais disponíveis para adoção"
)
public interface AnimalControllerDoc {

    @Operation(
            summary = "Cadastrar animal",
            description = "Cadastra um novo animal no sistema."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Animal cadastrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados do animal inválidos",
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
    Animal salvar(Animal animal);

    @Operation(
            summary = "Listar animais",
            description = "Retorna todos os animais cadastrados no sistema."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Animais encontrados com sucesso"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Nenhum animal encontrado"
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
    List<Animal> listar();

    @Operation(
            summary = "Atualizar animal",
            description = "Atualiza os dados de um animal existente."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Animal atualizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Animal não encontrado",
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
    Animal atualizar(Long id, Animal atualizado);

    @Operation(
            summary = "Remover animal por ID",
            description = "Remove um animal cadastrado no sistema."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Animal removido com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Animal não encontrado",
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