package br.com.ecogestor.mapper;

import br.com.ecogestor.dto.request.PontoColetaRequest;
import br.com.ecogestor.dto.request.ResiduosRequest;
import br.com.ecogestor.dto.response.PontoColetaResponse;
import br.com.ecogestor.dto.response.ResiduosResponse;
import br.com.ecogestor.entidade.PontoColeta;
import br.com.ecogestor.entidade.Residuos;
import org.springframework.stereotype.Component;
@Component
public class ResiduosMapper {

    public Residuos toEntity(ResiduosRequest residuosRequest){
        Residuos residuos = new Residuos();

        residuos.setTipoResiduo(residuosRequest.getTipoResiduo());
        residuos.setPeso(residuosRequest.getPeso());
        residuos.setNomeResponsavel(residuosRequest.getNomeResponsavel());


        return residuos;
    }

    public ResiduosResponse toResponse(Residuos entity) {
        ResiduosResponse response = new ResiduosResponse();

        response.setId(entity.getId());
        response.setPeso(entity.getPeso());
        response.setTipoResiduo(entity.getTipoResiduo());
        response.setNomeResponsavel(entity.getNomeResponsavel());

        return response;
    }

    public void atualizar(Residuos residuos, ResiduosRequest request) {
        residuos.setPeso(request.getPeso());
        residuos.setTipoResiduo(request.getTipoResiduo());
        residuos.setNomeResponsavel(request.getNomeResponsavel());

    }

}


