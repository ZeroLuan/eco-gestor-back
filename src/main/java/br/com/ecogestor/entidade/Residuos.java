package br.com.ecogestor.entidade;

import br.com.ecogestor.enums.EnumTipoResiduo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "residuos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Residuos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ponto_coleta_id")
    private PontoColeta pontoColeta;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_residuo", nullable = false)
    private EnumTipoResiduo tipoResiduo;

    @Column(name = "peso", nullable = false)
    private Double peso;

    @Column(name = "nome_responsavel")
    private String nomeResponsavel;

    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "data_fim")
    private LocalDateTime dataFim;
}
