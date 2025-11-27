package br.com.ecogestor.mapper;

import br.com.ecogestor.dto.request.PontoColetaRequest;
import br.com.ecogestor.dto.response.PontoColetaResponse;
import br.com.ecogestor.entidade.PontoColeta;
import org.springframework.stereotype.Component;

@Component
public class PontoColetaMapper {

    public PontoColeta toEntity(PontoColetaRequest pontoColetaRequest) {
        PontoColeta pontoColeta = new PontoColeta();

        pontoColeta.setNomePonto(pontoColetaRequest.getNomePonto());
        pontoColeta.setTipoResiduo(pontoColetaRequest.getTipoResiduo());
        pontoColeta.setMateriaisAceitos(pontoColetaRequest.getMateriaisAceitos());
        pontoColeta.setAtivo(pontoColetaRequest.getAtivo());

        return pontoColeta;
    }

    public PontoColetaResponse toResponse(PontoColeta entity) {
        PontoColetaResponse response = new PontoColetaResponse();

        response.setId(entity.getId());
        response.setNomePonto(entity.getNomePonto());
        response.setTipoResiduo(entity.getTipoResiduo());
        response.setMateriaisAceitos(entity.getMateriaisAceitos());
        response.setAtivo(entity.getAtivo());


        return response;
    }

    public void atualizar(PontoColeta pontoColeta, PontoColetaRequest request) {
        pontoColeta.setNomePonto(request.getNomePonto());
        pontoColeta.setTipoResiduo(request.getTipoResiduo());
        pontoColeta.setMateriaisAceitos(request.getMateriaisAceitos());
        pontoColeta.setAtivo(request.getAtivo());
    }

}
