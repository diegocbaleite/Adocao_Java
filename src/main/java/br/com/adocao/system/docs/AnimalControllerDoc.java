package br.com.adocao.system.docs;

import br.com.adocao.system.dto.AnimalRequestDTO;
import br.com.adocao.system.dto.AnimalResponseDTO;
import br.com.adocao.system.dto.error.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.http.ResponseEntity;

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
                    responseCode = "201",
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
            )
    })
    ResponseEntity<AnimalResponseDTO> salvar(
            AnimalRequestDTO dto
    );

    @Operation(
            summary = "Listar animais",
            description = "Lista os animais com paginação e filtro por status."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Animais encontrados"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Nenhum animal encontrado"
            )
    })
    ResponseEntity<List<AnimalResponseDTO>> listar(
            String status,
            int page,
            int size
    );

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
            )
    })
    ResponseEntity<AnimalResponseDTO> atualizar(
            Long id,
            AnimalRequestDTO dto
    );

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
            )
    })
    ResponseEntity<Void> deletar(Long id);
}