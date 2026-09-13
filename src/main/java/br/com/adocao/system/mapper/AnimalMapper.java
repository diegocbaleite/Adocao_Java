package br.com.adocao.system.mapper;

import br.com.adocao.system.dto.AnimalRequestDTO;
import br.com.adocao.system.dto.AnimalResponseDTO;
import br.com.adocao.system.model.Animal;

public class AnimalMapper {

    private AnimalMapper() {
    }

    public static Animal toEntity(AnimalRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        Animal animal = new Animal();

        animal.setNome(dto.nome());
        animal.setEspecie(dto.especie());
        animal.setRaca(dto.raca());
        animal.setIdade(dto.idade());
        animal.setDescricao(dto.descricao());
        animal.setFotoUrl(dto.fotoUrl());
        animal.setStatus(dto.status());

        return animal;
    }

    public static AnimalResponseDTO toResponse(Animal animal) {

        if (animal == null) {
            return null;
        }

        Long idAbrigo = animal.getAbrigo() != null
                ? animal.getAbrigo().getIdAbrigo()
                : null;

        return new AnimalResponseDTO(
                animal.getId(),
                idAbrigo,
                animal.getNome(),
                animal.getEspecie(),
                animal.getRaca(),
                animal.getIdade(),
                animal.getDescricao(),
                animal.getFotoUrl(),
                animal.getStatus()
        );
    }
}