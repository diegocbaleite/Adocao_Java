package br.com.adocao.system.mapper;

import br.com.adocao.system.dto.UsuarioDTO;
import br.com.adocao.system.model.Usuario;

public class UsuarioMapper {

    public static UsuarioDTO toDTO(Usuario u) {
        if (u == null) return null;

        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setEmail(u.getEmail());
        dto.setCpf(u.getCpf());
        dto.setIdade(u.getIdade());
        dto.setTelefone(u.getTelefone());
        dto.setEndereco(u.getEndereco());
        dto.setSenha(u.getSenha());
        dto.setAtivo(u.getAtivo());
        dto.setDataCadastro(u.getDataCadastro());
        return dto;
    }

    public static Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) return null;

        Usuario u = new Usuario();
        u.setId(dto.getId());
        u.setNome(dto.getNome());
        u.setEmail(dto.getEmail());
        u.setCpf(dto.getCpf());
        u.setIdade(dto.getIdade());
        u.setTelefone(dto.getTelefone());
        u.setEndereco(dto.getEndereco());
        u.setSenha(dto.getSenha());
        u.setAtivo(dto.getAtivo());
        u.setDataCadastro(dto.getDataCadastro());
        return u;
    }
}
