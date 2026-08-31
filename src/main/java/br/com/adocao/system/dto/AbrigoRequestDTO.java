package br.com.adocao.system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AbrigoRequestDTO(

        @NotBlank(message = "O nome do abrigo é obrigatório")
        @Size(max = 100)
        String nome,

        @Size(max = 20)
        String telefone,

        @Size(max = 200)
        String endereco,

        @Email(message = "E-mail inválido")
        @Size(max = 120)
        String email
) {
}