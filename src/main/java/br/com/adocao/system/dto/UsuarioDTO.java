package br.com.adocao.system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    @Size(max = 120, message = "O e-mail deve ter no máximo 120 caracteres")
    private String email;

    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
    private String telefone;

    @Size(max = 200, message = "O endereço deve ter no máximo 200 caracteres")
    private String endereco;

    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    // Campo dataCadastro normalmente não é enviado pelo usuário,
    // mas podemos incluir para exibição ou leitura
    private java.time.LocalDateTime dataCadastro;

    private Boolean ativo;
}
