package br.com.ecogestor.dto.response;

import br.com.ecogestor.enums.EnumStatus;
import br.com.ecogestor.enums.EnumTipoLicenca;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class LicencaAmbientalResponse {


    private Long id;
    private String numeroLicenca;
    private EnumTipoLicenca tipoLicenca;
    private EnumStatus statusLicenca;
    private LocalDateTime dataValidade;
    private LocalDateTime dataEmissao;
    private Long cooperativaId;

    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
}
