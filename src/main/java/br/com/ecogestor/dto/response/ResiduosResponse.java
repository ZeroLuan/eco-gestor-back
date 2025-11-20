package br.com.ecogestor.dto.response;

import br.com.ecogestor.enums.EnumTipoResiduo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ResiduosResponse {

    private Long id;
    private EnumTipoResiduo tipoResiduo;
    private Double peso;
    private String nomeResponsavel;

    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
}