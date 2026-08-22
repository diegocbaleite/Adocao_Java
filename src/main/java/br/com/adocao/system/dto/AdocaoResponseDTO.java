package br.com.adocao.system.dto;

import java.time.LocalDate;

public record AdocaoResponseDTO(
        Long idAdocao,
        Long idUsuario,
        Long idAnimal,
        LocalDate dataAdocao,
        Boolean aprovado
) {
}
