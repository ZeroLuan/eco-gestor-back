package br.com.ecogestor.licenca.mapper;

import br.com.ecogestor.licenca.dto.request.LicencaAmbientalRequest;
import br.com.ecogestor.licenca.dto.response.LicencaAmbientalResponse;
import br.com.ecogestor.licenca.entity.LicencaAmbiental;
import org.springframework.stereotype.Component;

@Component
public class LicencaAmbientalMapper {

    public LicencaAmbiental toEntity(LicencaAmbientalRequest licencaAmbientalRequest) {
        LicencaAmbiental licencaAmbiental = new LicencaAmbiental();

        licencaAmbiental.setNumeroLicenca(licencaAmbientalRequest.getNumeroLicenca());
        licencaAmbiental.setTipoLicenca(licencaAmbientalRequest.getTipoLicenca());
        licencaAmbiental.setStatusLicenca(licencaAmbientalRequest.getStatusLicenca());
        licencaAmbiental.setDataValidade(licencaAmbientalRequest.getDataValidade());
        licencaAmbiental.setDataEmissao(licencaAmbientalRequest.getDataEmissao());

        return licencaAmbiental;
    }

    public LicencaAmbientalResponse toResponse(LicencaAmbiental entity) {
        LicencaAmbientalResponse response = new LicencaAmbientalResponse();

        response.setId(entity.getId());
        response.setNumeroLicenca(entity.getNumeroLicenca());
        response.setTipoLicenca(entity.getTipoLicenca());
        response.setStatusLicenca(entity.getStatusLicenca());
        response.setDataValidade(entity.getDataValidade());
        response.setDataEmissao(entity.getDataEmissao());
        response.setDataInicio(entity.getDataInicio());
        response.setDataFim(entity.getDataFim());

        if (entity.getCooperativa() != null) {
            response.setCooperativaId(entity.getCooperativa().getId());
        }

        return response;
    }

    public void atualizar(LicencaAmbiental licencaAmbiental, LicencaAmbientalRequest request) {
        licencaAmbiental.setNumeroLicenca(request.getNumeroLicenca());
        licencaAmbiental.setTipoLicenca(request.getTipoLicenca());
        licencaAmbiental.setStatusLicenca(request.getStatusLicenca());
        licencaAmbiental.setDataValidade(request.getDataValidade());
        licencaAmbiental.setDataEmissao(request.getDataEmissao());
    }

}
