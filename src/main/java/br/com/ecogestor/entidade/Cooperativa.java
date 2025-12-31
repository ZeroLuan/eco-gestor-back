package br.com.ecogestor.entidade;

import br.com.ecogestor.enums.EnumStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_cooperativa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cooperativa extends Empresa {

    @Column(name = "nome_responsavel", nullable = false)
    private String responsavel;

    @Column(name = "status_cooperativa")
    @Enumerated(EnumType.STRING)
    private EnumStatus statusCooperativa;

    @OneToMany(mappedBy = "cooperativa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PontoColeta> pontosColetas;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.PERSIST)
    private List<LicencaAmbiental> licencasAmbientais;

    @Column(name = "status_licenca")
    @Enumerated(EnumType.STRING)
    private EnumStatus statusLicenca;

}
