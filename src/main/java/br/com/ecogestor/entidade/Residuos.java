package br.com.ecogestor.entidade;

import br.com.ecogestor.enums.EnumTipoResiduo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_residuos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Residuos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_residuo", nullable = false)
    private EnumTipoResiduo tipoResiduo;

    @Column(name = "peso", nullable = false)
    private Double peso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_ponto_coleta", nullable = false)
    private PontoColeta pontoColeta;

    @Column(name = "nome_responsavel")
    private String nomeResponsavel;

    @Column(name = "data_coleta")
    private LocalDate dataColeta;

    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "data_fim")
    private LocalDateTime dataFim;
}
