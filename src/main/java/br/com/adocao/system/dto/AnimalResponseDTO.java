package br.com.adocao.system.dto;

import br.com.adocao.system.enums.StatusAnimal;

public record AnimalResponseDTO(
        Long id,
        Long idAbrigo,
        String nome,
        String especie,
        String raca,
        Integer idade,
        String descricao,
        String fotoUrl,
        StatusAnimal status
) {
}