package br.com.ecogestor.entidade;

import br.com.ecogestor.enums.EnumStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cooperativa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cooperativa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "nome_responsavel", nullable = false)
    private String responsavel;

    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    @Column(name = "status_cooperativa")
    @Enumerated(EnumType.STRING)
    private EnumStatus statusCooperativa;

    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "data_fim")
    private LocalDateTime dataFim;


}
