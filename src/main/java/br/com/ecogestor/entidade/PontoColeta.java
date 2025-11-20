package br.com.ecogestor.entidade;

import br.com.ecogestor.enums.EnumTipoResiduo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ponto_coleta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PontoColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_ponto", nullable = false)
    private String nomePonto;

    @Column(name = "ativo")
    private Boolean ativo;

    @Column(name = "tipo_residuo")
    @Enumerated(EnumType.STRING)
    private EnumTipoResiduo tipoResiduo;

    // Muitos pontos de coleta podem ter um endereço
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "ponto_coleta_materiais",
            joinColumns = @JoinColumn(name = "ponto_id")
    )
    @Column(name = "material_aceito")
    @Enumerated(EnumType.STRING)
    private List<EnumTipoResiduo> materiaisAceitos;

    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "data_fim")
    private LocalDateTime dataFim;
}