package br.com.ecogestor.pontocoleta.dto.request;

import br.com.ecogestor.shared.enums.EnumTipoResiduo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class PontoColetaRequest {

    private Long id;
    private String nomePonto;
    private EnumTipoResiduo tipoResiduo;
    private List<EnumTipoResiduo> materiaisAceitos;
    private Boolean ativo;
    private Long enderecoId; // referencia ao endereço existente
    private Long cooperativaId; // referencia a cooperativa
    // Campo utilizado apenas para filtro na busca. No front é exibido como "Endereço" (nome do endereço)
    private String enderecoNome;
}