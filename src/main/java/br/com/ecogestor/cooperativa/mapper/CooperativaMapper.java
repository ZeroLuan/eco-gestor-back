package br.com.ecogestor.cooperativa.mapper;

import br.com.ecogestor.cooperativa.dto.request.CooperativaRequest;
import br.com.ecogestor.cooperativa.dto.response.CooperativaResponse;
import br.com.ecogestor.cooperativa.entity.Cooperativa;
import br.com.ecogestor.endereco.mapper.EnderecoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CooperativaMapper {

    @Autowired
    private EnderecoMapper enderecoMapper;

    public Cooperativa toEntity(CooperativaRequest cooperativaRequest) {
        Cooperativa cooperativa = new Cooperativa();

        cooperativa.setNomeEmpresa(cooperativaRequest.getNomeEmpresa());
        cooperativa.setNomeFantasia(cooperativaRequest.getNomeFantasia());
        cooperativa.setCnpj(cooperativaRequest.getCnpj());
        cooperativa.setTelefone(cooperativaRequest.getTelefone());
        cooperativa.setEmail(cooperativaRequest.getEmail());
        cooperativa.setNaturezaJuridica(cooperativaRequest.getNaturezaJuridica());
        cooperativa.setCnae(cooperativaRequest.getCnae());

        cooperativa.setResponsavel(cooperativaRequest.getNomeResponsavel());
        cooperativa.setStatusCooperativa(cooperativaRequest.getStatusCooperativa());

        return cooperativa;
    }

    public CooperativaResponse toResponse(Cooperativa entity) {
        CooperativaResponse response = new CooperativaResponse();

        response.setId(entity.getId());
        response.setNome(entity.getNomeEmpresa());
        response.setResponsavel(entity.getResponsavel());
        response.setTelefone(entity.getTelefone());
        response.setCnpj(entity.getCnpj());
        response.setStatusCooperativa(entity.getStatusCooperativa());
        response.setDataInicio(entity.getDataInicio());
        response.setDataFim(entity.getDataFim());

        if (entity.getEndereco() != null) {
            response.setEndereco(enderecoMapper.toResponse(entity.getEndereco()));
        }

        return response;
    }

    public void atualizar(Cooperativa cooperativa, CooperativaRequest request) {
        cooperativa.setNomeEmpresa(request.getNomeEmpresa());
        cooperativa.setNomeFantasia(request.getNomeFantasia());
        cooperativa.setCnpj(request.getCnpj());
        cooperativa.setTelefone(request.getTelefone());
        cooperativa.setEmail(request.getEmail());
        cooperativa.setNaturezaJuridica(request.getNaturezaJuridica());
        cooperativa.setCnae(request.getCnae());
        cooperativa.setResponsavel(request.getNomeResponsavel());
        cooperativa.setStatusCooperativa(request.getStatusCooperativa());
    }

}

