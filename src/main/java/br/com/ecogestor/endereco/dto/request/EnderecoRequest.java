package br.com.ecogestor.endereco.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class EnderecoRequest {

    private String bairro;
    private String cep;
    private String cidade;
    private String complemento;
    private String estado;
    private String logradouro;
    private String numero;
    private LocalDateTime dataInicio;

}