package br.com.adocao.system.service;

import br.com.adocao.system.dto.UsuarioDTO;
import br.com.adocao.system.mapper.UsuarioMapper;
import br.com.adocao.system.model.Usuario;
import br.com.adocao.system.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    // BUSCAR (GET por ID)
    public UsuarioDTO buscar(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        return UsuarioMapper.toDTO(usuario);
    }

    // CRIAR (INSERT)
    public UsuarioDTO criar(UsuarioDTO dto) {
        validarCpfEmailIdade(dto);

        Usuario usuario = UsuarioMapper.toEntity(dto);
        usuario.setAtivo(dto.getAtivo() != null ? dto.getAtivo() : true);

        Usuario salvo = repository.save(usuario);
        return UsuarioMapper.toDTO(salvo);
    }

    // ATUALIZAR (UPDATE)
    public UsuarioDTO atualizar(Long id, UsuarioDTO dto) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        if (!usuario.getCpf().equals(dto.getCpf())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O CPF não pode ser alterado");
        }

        if (!usuario.getEmail().equals(dto.getEmail()) && repository.existsByEmail(dto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail já em uso");
        }

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setIdade(dto.getIdade());
        usuario.setTelefone(dto.getTelefone());
        usuario.setEndereco(dto.getEndereco());
        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            usuario.setSenha(dto.getSenha()); // Atualiza a senha se fornecida
        }
        usuario.setAtivo(dto.getAtivo() != null ? dto.getAtivo() : usuario.getAtivo());

        Usuario atualizado = repository.save(usuario);
        return UsuarioMapper.toDTO(atualizado);
    }

    // LISTAR (com paginação e filtro por status)
    public List<UsuarioDTO> listar(String status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Usuario> pagina;

        if (status != null && !status.isBlank()) {
            Boolean ativo = status.equalsIgnoreCase("ativo") || status.equalsIgnoreCase("true");
            pagina = repository.findByAtivo(ativo, pageable);
        } else {
            pagina = repository.findAll(pageable);
        }

        return pagina.stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    // EXCLUIR (DELETE)
    public void deletar(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        repository.delete(usuario);
    }

    // VALIDAÇÕES AUXILIARES
    private void validarCpfEmailIdade(UsuarioDTO dto) {
        if (repository.existsByCpf(dto.getCpf())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF já cadastrado");
        }

        if (repository.existsByEmail(dto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail já cadastrado");
        }

        if (dto.getIdade() < 18) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Idade mínima para cadastro é 18 anos");
        }
    }
}
