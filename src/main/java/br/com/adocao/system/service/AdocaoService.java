package br.com.adocao.system.service;

import br.com.adocao.system.dto.AdocaoRequestDTO;
import br.com.adocao.system.dto.AdocaoResponseDTO;
import br.com.adocao.system.mapper.AdocaoMapper;
import br.com.adocao.system.model.Adocao;
import br.com.adocao.system.model.Animal;
import br.com.adocao.system.model.Usuario;
import br.com.adocao.system.repository.AdocaoRepository;
import br.com.adocao.system.repository.AnimalRepository;
import br.com.adocao.system.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class AdocaoService {

    private final AdocaoRepository adocaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final AnimalRepository animalRepository;

    // =====================================================
    // CRIAR ADOÇÃO
    // =====================================================

    public AdocaoResponseDTO criar(AdocaoRequestDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.idUsuario())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Usuário não encontrado"
                        )
                );

        Animal animal = animalRepository.findById(dto.idAnimal())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Animal não encontrado"
                        )
                );

        if (animal.getStatus() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O animal não possui status definido"
            );
        }

        if (!animal.getStatus().name().equals("DISPONIVEL")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O animal não está disponível para adoção"
            );
        }

        Adocao adocao = AdocaoMapper.toEntity(dto);

        adocao.setUsuario(usuario);
        adocao.setAnimal(animal);
        adocao.setDataAdocao(LocalDate.now());
        adocao.setAprovado(false);

        Adocao salvo = adocaoRepository.save(adocao);

        return AdocaoMapper.toResponse(salvo);
    }

    // =====================================================
    // LISTAR
    // =====================================================

    public List<AdocaoResponseDTO> listar() {

        return adocaoRepository.findAll()
                .stream()
                .map(AdocaoMapper::toResponse)
                .toList();
    }

    // =====================================================
    // BUSCAR POR ID
    // =====================================================

    public AdocaoResponseDTO buscar(Long id) {

        Adocao adocao = adocaoRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Adoção não encontrada"
                        )
                );

        return AdocaoMapper.toResponse(adocao);
    }

    // =====================================================
    // ATUALIZAR
    // =====================================================

    public AdocaoResponseDTO atualizar(
            Long id,
            AdocaoRequestDTO dto
    ) {

        Adocao adocao = adocaoRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Adoção não encontrada"
                        )
                );

        Usuario usuario = usuarioRepository.findById(dto.idUsuario())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Usuário não encontrado"
                        )
                );

        Animal animal = animalRepository.findById(dto.idAnimal())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Animal não encontrado"
                        )
                );

        adocao.setUsuario(usuario);
        adocao.setAnimal(animal);

        Adocao atualizado = adocaoRepository.save(adocao);

        return AdocaoMapper.toResponse(atualizado);
    }

    // =====================================================
    // EXCLUIR
    // =====================================================

    public void deletar(Long id) {

        Adocao adocao = adocaoRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Adoção não encontrada"
                        )
                );

        adocaoRepository.delete(adocao);
    }

    @Transactional
    public AdocaoResponseDTO aprovar(Long id) {

        Adocao adocao = adocaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Adoção não encontrada"
                ));

        return AdocaoMapper.toResponse(adocao);
    }

}