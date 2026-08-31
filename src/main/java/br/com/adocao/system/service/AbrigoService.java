package br.com.adocao.system.service;

import br.com.adocao.system.dto.AbrigoRequestDTO;
import br.com.adocao.system.dto.AbrigoResponseDTO;
import br.com.adocao.system.mapper.AbrigoMapper;
import br.com.adocao.system.model.Abrigo;
import br.com.adocao.system.repository.AbrigoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@Service
public class AbrigoService {

    private final AbrigoRepository abrigoRepository;

    // BUSCAR POR ID
    public AbrigoResponseDTO buscar(Long id) {

        Abrigo abrigo = abrigoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Abrigo não encontrado"
                ));

        return AbrigoMapper.toResponse(abrigo);
    }

    // CRIAR
    public AbrigoResponseDTO criar(AbrigoRequestDTO dto) {

        if (abrigoRepository.existsByNome(dto.nome())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Já existe um abrigo com este nome"
            );
        }

        Abrigo abrigo = AbrigoMapper.toEntity(dto);

        Abrigo salvo = abrigoRepository.save(abrigo);

        return AbrigoMapper.toResponse(salvo);
    }

    // LISTAR
    public List<AbrigoResponseDTO> listar() {

        return abrigoRepository.findAll()
                .stream()
                .map(AbrigoMapper::toResponse)
                .toList();
    }

    // BUSCAR POR NOME
    public List<AbrigoResponseDTO> buscarPorNome(String nome) {

        return abrigoRepository.findAll()
                .stream()
                .filter(abrigo ->
                        abrigo.getNome()
                                .toLowerCase()
                                .contains(nome.toLowerCase())
                )
                .map(AbrigoMapper::toResponse)
                .toList();
    }

    // ATUALIZAR
    public AbrigoResponseDTO atualizar(
            Long id,
            AbrigoRequestDTO dto
    ) {

        Abrigo abrigo = abrigoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Abrigo não encontrado"
                ));

        if (!abrigo.getNome().equals(dto.nome())
                && abrigoRepository.existsByNome(dto.nome())) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Já existe um abrigo com este nome"
            );
        }

        abrigo.setNome(dto.nome());
        abrigo.setTelefone(dto.telefone());
        abrigo.setEndereco(dto.endereco());
        abrigo.setEmail(dto.email());

        Abrigo atualizado = abrigoRepository.save(abrigo);

        return AbrigoMapper.toResponse(atualizado);
    }

    // EXCLUIR
    public void deletar(Long id) {

        Abrigo abrigo = abrigoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Abrigo não encontrado"
                ));

        abrigoRepository.delete(abrigo);
    }
}