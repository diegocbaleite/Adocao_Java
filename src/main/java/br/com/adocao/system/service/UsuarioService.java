package br.com.adocao.system.service;

import br.com.adocao.system.dto.UsuarioRequestDTO;
import br.com.adocao.system.dto.UsuarioResponseDTO;
import br.com.adocao.system.mapper.UsuarioMapper;
import br.com.adocao.system.model.Usuario;
import br.com.adocao.system.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // BUSCAR POR ID
    public UsuarioResponseDTO buscar(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado"
                ));

        return UsuarioMapper.toResponseDTO(usuario);
    }

    // CRIAR
    public UsuarioResponseDTO criar(UsuarioRequestDTO dto) {

        validarCpfEmailIdade(dto);
        Usuario usuario = UsuarioMapper.toEntity(dto);
        Usuario salvo = usuarioRepository.save(usuario);
        return UsuarioMapper.toResponseDTO(salvo);
    }

    // ATUALIZAR
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado"
                ));

        // CPF não pode ser alterado
        if (!usuario.getCpf().equals(dto.cpf())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O CPF não pode ser alterado"
            );
        }

        // Verifica se o e-mail foi alterado e se já está em uso
        if (!usuario.getEmail().equals(dto.email())
                && usuarioRepository.existsByEmail(dto.email())) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "E-mail já em uso"
            );
        }

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setIdade(dto.idade());
        usuario.setTelefone(dto.telefone());
        usuario.setEndereco(dto.endereco());

        // Só altera a senha se uma nova senha for enviada
        if (dto.senha() != null && !dto.senha().isBlank()) {
            usuario.setSenha(dto.senha());
        }

        Usuario atualizado = usuarioRepository.save(usuario);

        return UsuarioMapper.toResponseDTO(atualizado);
    }

    // LISTAR COM PAGINAÇÃO E FILTRO POR STATUS
    public List<UsuarioResponseDTO> listar(
            String status,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Usuario> pagina;

        if (status != null && !status.isBlank()) {

            Boolean ativo =
                    status.equalsIgnoreCase("ativo")
                            || status.equalsIgnoreCase("true");

            pagina = usuarioRepository.findByAtivo(ativo, pageable);

        } else {

            pagina = usuarioRepository.findAll(pageable);
        }

        return pagina.getContent()
                .stream()
                .map(UsuarioMapper::toResponseDTO)
                .toList();
    }

    // EXCLUIR
    public void deletar(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado"
                ));

        usuarioRepository.delete(usuario);
    }

    // VALIDAÇÕES
    private void validarCpfEmailIdade(UsuarioRequestDTO dto) {

        if (usuarioRepository.existsByCpf(dto.cpf())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "CPF já cadastrado"
            );
        }

        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "E-mail já cadastrado"
            );
        }

        if (dto.idade() < 18) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Idade mínima para cadastro é 18 anos"
            );
        }
    }
}