package br.com.adocao.system.dto;

import jakarta.validation.constraints.*;

public record UsuarioRequestDTO(

        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 100)
        String nome,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "E-mail inválido")
        @Size(max = 120)
        String email,

        @Size(max = 20)
        String telefone,

        @Size(max = 200)
        String endereco,

        @NotBlank(message = "O CPF é obrigatório")
        @Pattern(
                regexp = "\\d{11}",
                message = "CPF deve conter 11 dígitos numéricos"
        )
        String cpf,

        @NotNull(message = "A idade é obrigatória")
        @Min(value = 18, message = "Idade mínima é 18 anos")
        @Max(value = 120, message = "Idade máxima é 120 anos")
        Integer idade,

        @NotBlank(message = "A senha é obrigatória")
        String senha
) {
}
