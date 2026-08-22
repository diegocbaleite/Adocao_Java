package br.com.adocao.system.mapper;

import br.com.adocao.system.dto.UsuarioRequestDTO;
import br.com.adocao.system.dto.UsuarioResponseDTO;
import br.com.adocao.system.model.Usuario;

public class UsuarioMapper {

    private UsuarioMapper() {
    }

    public static Usuario toEntity(UsuarioRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setCpf(dto.cpf());
        usuario.setIdade(dto.idade());
        usuario.setTelefone(dto.telefone());
        usuario.setEndereco(dto.endereco());
        usuario.setSenha(dto.senha());

        return usuario;
    }

    public static UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getEndereco(),
                usuario.getCpf(),
                usuario.getIdade(),
                usuario.getDataCadastro() != null
                        ? usuario.getDataCadastro().toString()
                        : null,
                usuario.getAtivo()
        );
    }
}
