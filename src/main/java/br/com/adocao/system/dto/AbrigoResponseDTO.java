package br.com.adocao.system.dto;

public record AbrigoResponseDTO(
        Long idAbrigo,
        String nome,
        String telefone,
        String endereco,
        String email
) {
}
