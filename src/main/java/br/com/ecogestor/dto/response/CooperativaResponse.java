package br.com.ecogestor.dto.response;

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
public class CooperativaResponse {

    private Long id;
    private String nome;
    private String responsavel;
    private String cnpj;
    private String statusCooperativa;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

}
