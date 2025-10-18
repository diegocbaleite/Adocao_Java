package br.com.adocao.system.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Adocao")
public class adocao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private usuario usuario;

    @ManyToOne
    private animal animal;

    private LocalDate dataAdocao = LocalDate.now();
    private boolean aprovado = false;
}

