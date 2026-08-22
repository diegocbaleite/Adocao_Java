package br.com.adocao.system.dto;

import br.com.adocao.system.enums.StatusAnimal;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AnimalRequestDTO(

        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 100)
        String nome,

        @NotBlank(message = "A espécie é obrigatória")
        @Size(max = 50)
        String especie,

        @Size(max = 50)
        String raca,

        @NotNull(message = "A idade é obrigatória")
        @Min(value = 0, message = "A idade não pode ser negativa")
        Integer idade,

        @Size(max = 255)
        String descricao,

        @Size(max = 255)
        String fotoUrl,

        StatusAnimal status,

        Long idAbrigo
) {
}
