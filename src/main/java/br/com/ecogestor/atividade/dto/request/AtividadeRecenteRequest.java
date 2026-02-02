package br.com.ecogestor.atividade.dto.request;

import br.com.ecogestor.shared.enums.EnumTipoAtividade;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class AtividadeRecenteRequest {

    private EnumTipoAtividade tipo;
    private String titulo;
    private String descricao;
    private Long entidadeId;
    private String entidadeTipo;

}
