package br.com.adocao.system.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private String cpf;
    private Integer idade;
    private String senha;
    private Boolean ativo;
    private LocalDateTime dataCadastro;
}
