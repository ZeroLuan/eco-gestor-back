package br.com.ecogestor.cooperativa.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CooperativaRequest {

    // Empresa
    private String nomeEmpresa;
    private String nomeFantasia;
    private String cnpj;
    private String telefone;
    private String email;
    private String naturezaJuridica;
    private String cnae;

    // Cooperativa
    private String nomeResponsavel;
    private Boolean statusCooperativa;

    // Endereço
    private Long enderecoId;
    private String enderecoNome;

}
