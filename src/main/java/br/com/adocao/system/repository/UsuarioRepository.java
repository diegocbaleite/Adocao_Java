package br.com.adocao.system.repository;

import br.com.adocao.system.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByNomeIgnoreCase(String nome);

    Page<Usuario> findByAtivo(Boolean ativo, Pageable pageable);

    List<Usuario> findByNomeContainingIgnoreCase(String nome);
}
