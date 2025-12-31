package br.com.ecogestor.mapper;


import br.com.ecogestor.dto.request.CooperativaRequest;
import br.com.ecogestor.dto.request.PontoColetaRequest;
import br.com.ecogestor.dto.response.CooperativaResponse;
import br.com.ecogestor.dto.response.PontoColetaResponse;
import br.com.ecogestor.entidade.Cooperativa;
import br.com.ecogestor.entidade.PontoColeta;
import br.com.ecogestor.repository.PontoColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CooperativaMapper {


    public Cooperativa toEntity(CooperativaRequest cooperativaRequest) {
        Cooperativa cooperativa = new Cooperativa();

        cooperativa.setResponsavel(cooperativaRequest.getResponsavel());
        cooperativa.setCnpj(cooperativaRequest.getCnpj());
        cooperativa.setStatusCooperativa(cooperativaRequest.getStatusCooperativa());

        return cooperativa;
    }

    public CooperativaResponse toResponse(Cooperativa entity) {
        CooperativaResponse response = new CooperativaResponse();

        response.setId(entity.getId());
        response.setResponsavel(entity.getResponsavel());
        response.setCnpj(entity.getCnpj());
        response.setStatusCooperativa(
                entity.getStatusCooperativa() != null ? entity.getStatusCooperativa().name() : null
        );
        response.setDataInicio(entity.getDataInicio());
        response.setDataFim(entity.getDataFim());


        return response;
    }


    public void atualizar(Cooperativa cooperativa, CooperativaRequest request) {
        cooperativa.setResponsavel(request.getResponsavel());
        cooperativa.setCnpj(request.getCnpj());
        cooperativa.setStatusCooperativa(request.getStatusCooperativa());

    }

}

