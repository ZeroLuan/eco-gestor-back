package br.com.ecogestor.mapper;

import br.com.ecogestor.dto.request.PontoColetaRequest;
import br.com.ecogestor.dto.request.ResiduosRequest;
import br.com.ecogestor.dto.response.PontoColetaResponse;
import br.com.ecogestor.dto.response.ResiduosResponse;
import br.com.ecogestor.entidade.PontoColeta;
import br.com.ecogestor.entidade.Residuos;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ResiduosMapper {

    public Residuos toEntity(ResiduosRequest residuosRequest){
        Residuos residuos = new Residuos();

        residuos.setTipoResiduo(residuosRequest.getTipoResiduo());
        residuos.setPeso(residuosRequest.getPeso());
        residuos.setNomeResponsavel(residuosRequest.getNomeResponsavel());
        residuos.setDataColeta(residuosRequest.getDataColeta());


        return residuos;
    }

    public ResiduosResponse toResponse(Residuos entity) {
        ResiduosResponse response = new ResiduosResponse();

        response.setId(entity.getId());
        response.setDataColeta(entity.getDataColeta());
        response.setTipoResiduo(entity.getTipoResiduo());
        response.setPeso(entity.getPeso());
        response.setLocal(entity.getPontoColeta().getNomePonto());
        response.setNomeResponsavel(entity.getNomeResponsavel());
        response.setDataInicio(entity.getDataInicio());
        response.setDataFim(entity.getDataFim());

        return response;
    }

    public void atualizar(Residuos residuos, ResiduosRequest request) {
        residuos.setPeso(request.getPeso());
        residuos.setTipoResiduo(request.getTipoResiduo());
        residuos.setNomeResponsavel(request.getNomeResponsavel());
        residuos.setDataColeta(request.getDataColeta());

    }

}


