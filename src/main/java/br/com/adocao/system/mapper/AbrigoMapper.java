package br.com.adocao.system.mapper;

import br.com.adocao.system.dto.AbrigoRequestDTO;
import br.com.adocao.system.dto.AbrigoResponseDTO;
import br.com.adocao.system.model.Abrigo;

public class AbrigoMapper {

    private AbrigoMapper() {
    }

    public static Abrigo toEntity(AbrigoRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        Abrigo abrigo = new Abrigo();

        abrigo.setNome(dto.nome());
        abrigo.setTelefone(dto.telefone());
        abrigo.setEndereco(dto.endereco());
        abrigo.setEmail(dto.email());

        return abrigo;
    }

    public static AbrigoResponseDTO toResponse(Abrigo abrigo) {

        if (abrigo == null) {
            return null;
        }

        return new AbrigoResponseDTO(
                abrigo.getIdAbrigo(),
                abrigo.getNome(),
                abrigo.getTelefone(),
                abrigo.getEndereco(),
                abrigo.getEmail()
        );
    }
}