package br.com.adocao.system.dto;

import jakarta.validation.constraints.NotNull;

public record AdocaoRequestDTO(

        @NotNull(message = "O usuário é obrigatório")
        Long idUsuario,

        @NotNull(message = "O animal é obrigatório")
        Long idAnimal
) {
}
