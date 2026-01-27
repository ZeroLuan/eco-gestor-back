package br.com.ecogestor.pontocoleta.mapper;

import br.com.ecogestor.cooperativa.mapper.CooperativaMapper;
import br.com.ecogestor.endereco.mapper.EnderecoMapper;
import br.com.ecogestor.pontocoleta.entity.PontoColeta;
import br.com.ecogestor.pontocoleta.dto.request.PontoColetaRequest;
import br.com.ecogestor.pontocoleta.dto.response.PontoColetaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PontoColetaMapper {

    @Autowired
    private EnderecoMapper enderecoMapper;

    @Autowired
    private CooperativaMapper cooperativaMapper;

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

        if (entity.getEndereco() != null) {
            response.setEndereco(enderecoMapper.toResponse(entity.getEndereco()));
        }

        if (entity.getCooperativa() != null) {
            response.setCooperativa(cooperativaMapper.toResponse(entity.getCooperativa()));
        }


        return response;
    }

    public void atualizar(PontoColeta pontoColeta, PontoColetaRequest request) {
        pontoColeta.setNomePonto(request.getNomePonto());
        pontoColeta.setTipoResiduo(request.getTipoResiduo());
        pontoColeta.setMateriaisAceitos(request.getMateriaisAceitos());
        pontoColeta.setAtivo(request.getAtivo());
    }

}
