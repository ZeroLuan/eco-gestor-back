package br.com.ecogestor.residuos.mapper;

import br.com.ecogestor.residuos.dto.request.ResiduosRequest;
import br.com.ecogestor.residuos.dto.response.ResiduosResponse;
import br.com.ecogestor.residuos.entity.Residuos;
import org.springframework.stereotype.Component;

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


