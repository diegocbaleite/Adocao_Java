package br.com.adocao.system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @Column(length = 20)
    private String telefone;

    @Column(length = 200)
    private String endereco;

    @Column(nullable = false)
    private String senha;

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private java.time.LocalDateTime dataCadastro = java.time.LocalDateTime.now();

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;
}