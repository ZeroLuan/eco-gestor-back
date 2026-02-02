package br.com.ecogestor.atividade.entity;

import br.com.ecogestor.shared.enums.EnumTipoAtividade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "atividade_recente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtividadeRecente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumTipoAtividade tipo;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(nullable = false, length = 500)
    private String descricao;

    @Column(name = "usuario_email", length = 150)
    private String usuarioEmail;

    @Column(name = "entidade_id")
    private Long entidadeId;

    @Column(name = "entidade_tipo", length = 50)
    private String entidadeTipo;

    @Column(nullable = false)
    private LocalDateTime data;

    @PrePersist
    protected void onCreate() {
        if (data == null) {
            data = LocalDateTime.now();
        }
    }
}
