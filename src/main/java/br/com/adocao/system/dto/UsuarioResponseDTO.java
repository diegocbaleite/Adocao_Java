package br.com.adocao.system.dto;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String endereco,
        String cpf,
        Integer idade,
        String dataCadastro,
        Boolean ativo
) {
}
