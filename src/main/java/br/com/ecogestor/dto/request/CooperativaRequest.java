package br.com.ecogestor.dto.request;

import br.com.ecogestor.entidade.PontoColeta;
import br.com.ecogestor.enums.EnumStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CooperativaRequest {

    private String nome;
    private String responsavel;
    private String cnpj;
    private EnumStatus statusCooperativa;
}
