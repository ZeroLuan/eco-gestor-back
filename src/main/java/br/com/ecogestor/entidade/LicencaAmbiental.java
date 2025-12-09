package br.com.ecogestor.entidade;

import br.com.ecogestor.enums.EnumStatus;
import br.com.ecogestor.enums.EnumTipoLicenca;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "licenca-ambiental")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LicencaAmbiental {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_licenca")
    @Enumerated(EnumType.STRING)
    private EnumTipoLicenca tipoLicenca;

    @Column(name = "numero_licenca", nullable = false)
    private String numeroLicenca;

    @Column(name = "status_licenca")
    @Enumerated(EnumType.STRING)
    private EnumStatus statusLicenca;

    @Column(name = "validade")
    private LocalDateTime validade;

    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "data_fim")
    private LocalDateTime dataFim;
}
