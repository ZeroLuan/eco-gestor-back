package br.com.ecogestor.mapper;

import br.com.ecogestor.dto.request.LicencaAmbientalRequest;
import br.com.ecogestor.dto.response.LicencaAmbientalResponse;
import br.com.ecogestor.entidade.LicencaAmbiental;
import org.springframework.stereotype.Component;

@Component
public class LicencaAmbientalMapper {

    public LicencaAmbiental toEntity(LicencaAmbientalRequest licencaAmbientalRequest) {
        LicencaAmbiental licencaAmbiental = new LicencaAmbiental();

        licencaAmbiental.setNumeroLicenca(licencaAmbientalRequest.getNumeroLicenca());
        licencaAmbiental.setTipoLicenca(licencaAmbientalRequest.getTipoLicenca());
        licencaAmbiental.setStatusLicenca(licencaAmbientalRequest.getStatusLicenca());
        licencaAmbiental.setValidade(licencaAmbientalRequest.getValidade());

        return licencaAmbiental;
    }

    public LicencaAmbientalResponse toResponse(LicencaAmbiental entity) {
        LicencaAmbientalResponse response = new LicencaAmbientalResponse();

        response.setId(entity.getId());
        response.setNumeroLicenca(entity.getNumeroLicenca());
        response.setTipoLicenca(entity.getTipoLicenca());
        response.setStatusLicenca(entity.getStatusLicenca());
        response.setValidade(entity.getValidade());


        return response;
    }

    public void atualizar(LicencaAmbiental licencaAmbiental, LicencaAmbientalRequest request) {
        licencaAmbiental.setNumeroLicenca(request.getNumeroLicenca());
        licencaAmbiental.setTipoLicenca(request.getTipoLicenca());
        licencaAmbiental.setStatusLicenca(request.getStatusLicenca());
        licencaAmbiental.setValidade(request.getValidade());
    }

}
