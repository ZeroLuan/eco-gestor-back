package br.com.ecogestor.pontocoleta.dto.response;

import br.com.ecogestor.cooperativa.dto.response.CooperativaResponse;
import br.com.ecogestor.endereco.dto.response.EnderecoResponse;
import br.com.ecogestor.shared.enums.EnumTipoResiduo;
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
public class PontoColetaResponse {

    private Long id;
    private String nomePonto;
    private EnumTipoResiduo tipoResiduo;
    private EnderecoResponse endereco;
    private CooperativaResponse cooperativa;
    private Boolean ativo;
    private List<EnumTipoResiduo> materiaisAceitos;

    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
}