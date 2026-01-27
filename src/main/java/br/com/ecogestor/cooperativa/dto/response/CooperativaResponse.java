package br.com.ecogestor.cooperativa.dto.response;

import br.com.ecogestor.endereco.dto.response.EnderecoResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CooperativaResponse {

    private Long id;
    private String nome;
    private String responsavel;
    private String telefone;
    private String cnpj;
    private Boolean statusCooperativa;
    private EnderecoResponse endereco;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

}
