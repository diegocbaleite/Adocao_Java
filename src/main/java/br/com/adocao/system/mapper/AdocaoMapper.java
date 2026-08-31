package br.com.adocao.system.mapper;

import br.com.adocao.system.dto.AdocaoRequestDTO;
import br.com.adocao.system.dto.AdocaoResponseDTO;
import br.com.adocao.system.model.Adocao;

import java.time.LocalDate;

public class AdocaoMapper {

    private AdocaoMapper() {
    }

    public static Adocao toEntity(AdocaoRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        Adocao adocao = new Adocao();

        adocao.setDataAdocao(LocalDate.now());
        adocao.setAprovado(false);

        return adocao;
    }

    public static AdocaoResponseDTO toResponse(Adocao adocao) {

        if (adocao == null) {
            return null;
        }

        return new AdocaoResponseDTO(
                adocao.getId(),
                adocao.getUsuario().getId(),
                adocao.getAnimal().getId(),
                adocao.getDataAdocao(),
                adocao.getAprovado()
        );
    }
}