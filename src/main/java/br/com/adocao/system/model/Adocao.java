package br.com.adocao.system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "adocoes") // nome de tabela no plural e minúsculo é mais padrão
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O usuário é obrigatório.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @NotNull(message = "O animal é obrigatório.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @NotNull(message = "A data da adoção é obrigatória.")
    @Column(name = "data_adocao", nullable = false)
    private LocalDate dataAdocao = LocalDate.now();

    @Column(nullable = false)
    private boolean aprovado = false;
}
