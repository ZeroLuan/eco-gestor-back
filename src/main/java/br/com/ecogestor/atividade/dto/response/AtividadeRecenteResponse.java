package br.com.ecogestor.atividade.dto.response;

import br.com.ecogestor.shared.enums.EnumTipoAtividade;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtividadeRecenteResponse {
    
    private Long id;
    private EnumTipoAtividade tipo;
    private String tipoLabel;
    private String categoria;
    private String titulo;
    private String descricao;
    private String usuarioEmail;
    private Long entidadeId;
    private String entidadeTipo;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime data;
}
