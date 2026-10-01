package br.com.adocao.system.docs;

import br.com.adocao.system.dto.AdocaoRequestDTO;
import br.com.adocao.system.dto.AdocaoResponseDTO;
import br.com.adocao.system.dto.error.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Adoções", description = "Gerenciamento das adoções de animais")
public interface AdocaoControllerDoc {

    @Operation(summary = "Cadastrar uma adoção", description = "Registra uma solicitação de adoção.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Adoção cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou animal indisponível",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário ou animal não encontrado",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    AdocaoResponseDTO salvar(AdocaoRequestDTO dto);

    @Operation(summary = "Listar adoções", description = "Retorna todas as adoções cadastradas.")
    @ApiResponse(responseCode = "200", description = "Lista de adoções retornada")
    List<AdocaoResponseDTO> listar();

    @Operation(summary = "Buscar adoção por ID", description = "Retorna uma adoção cadastrada.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Adoção encontrada"),
            @ApiResponse(responseCode = "404", description = "Adoção não encontrada",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    AdocaoResponseDTO buscar(Long id);

    @Operation(summary = "Atualizar adoção", description = "Atualiza o usuário e o animal de uma adoção.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Adoção atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Adoção, usuário ou animal não encontrado",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    AdocaoResponseDTO atualizar(Long id, AdocaoRequestDTO dto);

    @Operation(summary = "Excluir adoção", description = "Exclui uma adoção pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Adoção excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Adoção não encontrada",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    void deletar(Long id);

    @Operation(summary = "Aprovar adoção", description = "Aprova a adoção e marca o animal como adotado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Adoção aprovada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Adoção não encontrada",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Adoção já aprovada ou animal indisponível",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    AdocaoResponseDTO aprovar(Long id);
}
