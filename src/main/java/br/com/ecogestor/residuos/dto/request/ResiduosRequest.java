package br.com.ecogestor.residuos.dto.request;

import br.com.ecogestor.shared.enums.EnumTipoResiduo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ResiduosRequest {

    private Long idPontoColeta;
    private EnumTipoResiduo tipoResiduo;
    private Double peso;
    private String nomeResponsavel;
    private LocalDate dataColeta;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String local;

}