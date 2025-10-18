package br.com.adocao.system.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String especie; // cachorro, gato, etc
    private String raca;
    private int idade;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusAnimal status = StatusAnimal.DISPONIVEL;
}

