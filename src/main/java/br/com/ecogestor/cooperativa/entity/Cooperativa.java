package br.com.ecogestor.cooperativa.entity;

import br.com.ecogestor.empresa.entity.Empresa;
import br.com.ecogestor.licenca.entity.LicencaAmbiental;
import br.com.ecogestor.pontocoleta.entity.PontoColeta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private Boolean statusCooperativa;

    @OneToMany(mappedBy = "cooperativa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PontoColeta> pontosColetas;

    @OneToMany(mappedBy = "cooperativa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LicencaAmbiental> licencasAmbientais;


}
