package br.com.ecogestor.atividade.mapper;

import br.com.ecogestor.atividade.dto.request.AtividadeRecenteRequest;
import br.com.ecogestor.atividade.dto.response.AtividadeRecenteResponse;
import br.com.ecogestor.atividade.entity.AtividadeRecente;
import br.com.ecogestor.shared.enums.EnumTipoAtividade;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AtividadeRecenteMapper {

    public AtividadeRecente toEntity(AtividadeRecenteRequest request, String usuarioEmail) {
        AtividadeRecente atividade = new AtividadeRecente();
        atividade.setTipo(request.getTipo());
        atividade.setTitulo(request.getTitulo());
        atividade.setDescricao(request.getDescricao());
        atividade.setUsuarioEmail(usuarioEmail);
        atividade.setEntidadeId(request.getEntidadeId());
        atividade.setEntidadeTipo(request.getEntidadeTipo());
        atividade.setData(LocalDateTime.now());
        return atividade;
    }

    public AtividadeRecenteResponse toResponse(AtividadeRecente entity) {
        AtividadeRecenteResponse response = new AtividadeRecenteResponse();
        
        response.setId(entity.getId());
        response.setTipo(entity.getTipo());
        response.setTipoLabel(entity.getTipo().getLabel());
        response.setCategoria(entity.getTipo().getCategoria());
        response.setTitulo(entity.getTitulo());
        response.setDescricao(entity.getDescricao());
        response.setUsuarioEmail(entity.getUsuarioEmail());
        response.setEntidadeId(entity.getEntidadeId());
        response.setEntidadeTipo(entity.getEntidadeTipo());
        response.setData(entity.getData());
        
        return response;
    }

    // Factory methods para criar requests
    public AtividadeRecenteRequest criarRequestPonto(Long pontoId, String nomePonto) {
        AtividadeRecenteRequest request = new AtividadeRecenteRequest();
        request.setTipo(EnumTipoAtividade.PONTO_CRIADO);
        request.setTitulo("Ponto de Coleta Criado");
        request.setDescricao("Novo ponto '" + nomePonto + "' cadastrado");
        request.setEntidadeId(pontoId);
        request.setEntidadeTipo("PontoColeta");
        return request;
    }

    public AtividadeRecenteRequest criarRequestResiduo(Long residuoId, Double peso, String tipoResiduo) {
        AtividadeRecenteRequest request = new AtividadeRecenteRequest();
        request.setTipo(EnumTipoAtividade.RESIDUO_CRIADO);
        request.setTitulo("Resíduo Registrado");
        request.setDescricao(peso + "kg de " + tipoResiduo + " coletado");
        request.setEntidadeId(residuoId);
        request.setEntidadeTipo("Residuo");
        return request;
    }

    public AtividadeRecenteRequest criarRequestLicenca(Long licencaId, String numeroLicenca, String tipoLicenca) {
        AtividadeRecenteRequest request = new AtividadeRecenteRequest();
        request.setTipo(EnumTipoAtividade.LICENCA_CRIADA);
        request.setTitulo("Licença Ambiental Emitida");
        request.setDescricao("Licença " + numeroLicenca + " (" + tipoLicenca + ") cadastrada");
        request.setEntidadeId(licencaId);
        request.setEntidadeTipo("LicencaAmbiental");
        return request;
    }

    public AtividadeRecenteRequest criarRequestCooperativa(Long cooperativaId, String nomeCooperativa) {
        AtividadeRecenteRequest request = new AtividadeRecenteRequest();
        request.setTipo(EnumTipoAtividade.COOPERATIVA_CRIADA);
        request.setTitulo("Cooperativa Cadastrada");
        request.setDescricao("Nova cooperativa '" + nomeCooperativa + "' registrada");
        request.setEntidadeId(cooperativaId);
        request.setEntidadeTipo("Cooperativa");
        return request;
    }
}
