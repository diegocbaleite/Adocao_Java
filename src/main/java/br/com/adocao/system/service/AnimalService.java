package br.com.adocao.system.service;

import br.com.adocao.system.dto.AnimalRequestDTO;
import br.com.adocao.system.dto.AnimalResponseDTO;
import br.com.adocao.system.mapper.AnimalMapper;
import br.com.adocao.system.model.Abrigo;
import br.com.adocao.system.model.Animal;
import br.com.adocao.system.repository.AbrigoRepository;
import br.com.adocao.system.repository.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final AbrigoRepository abrigoRepository;

    // BUSCAR POR ID
    public AnimalResponseDTO buscar(Long id) {

        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Animal não encontrado"
                ));

        return AnimalMapper.toResponse(animal);
    }

    // CRIAR
    public AnimalResponseDTO criar(AnimalRequestDTO dto) {

        validarDadosAnimal(dto);

        Abrigo abrigo = buscarAbrigo(dto.idAbrigo());

        Animal animal = AnimalMapper.toEntity(dto);
        animal.setAbrigo(abrigo);

        Animal salvo = animalRepository.save(animal);

        return AnimalMapper.toResponse(salvo);
    }

    // ATUALIZAR
    public AnimalResponseDTO atualizar(Long id, AnimalRequestDTO dto) {

        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Animal não encontrado"
                ));

        validarDadosAnimal(dto);

        Abrigo abrigo = buscarAbrigo(dto.idAbrigo());

        animal.setAbrigo(abrigo);
        animal.setNome(dto.nome());
        animal.setEspecie(dto.especie());
        animal.setRaca(dto.raca());
        animal.setIdade(dto.idade());
        animal.setDescricao(dto.descricao());
        animal.setFotoUrl(dto.fotoUrl());

        Animal atualizado = animalRepository.save(animal);

        return AnimalMapper.toResponse(atualizado);
    }

    // LISTAR COM PAGINAÇÃO E FILTRO POR STATUS
    public List<AnimalResponseDTO> listar(
            String status,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Animal> pagina;

        if (status != null && !status.isBlank()) {
            pagina = animalRepository.findByStatus(status, pageable);
        } else {
            pagina = animalRepository.findAll(pageable);
        }

        return pagina.getContent()
                .stream()
                .map(AnimalMapper::toResponse)
                .toList();
    }

    // EXCLUIR
    public void deletar(Long id) {

        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Animal não encontrado"
                ));

        animalRepository.delete(animal);
    }

    // BUSCAR O ABRIGO PARA ASSOCIAR AO ANIMAL
    private Abrigo buscarAbrigo(Long idAbrigo) {

        if (idAbrigo == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O abrigo é obrigatório"
            );
        }

        return abrigoRepository.findById(idAbrigo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Abrigo não encontrado"
                ));
    }

    // VALIDAÇÕES
    private void validarDadosAnimal(AnimalRequestDTO dto) {

        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Nome do animal é obrigatório"
            );
        }

        if (dto.especie() == null || dto.especie().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Espécie do animal é obrigatória"
            );
        }

        if (dto.idade() == null || dto.idade() < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Idade do animal deve ser válida"
            );
        }
    }
}