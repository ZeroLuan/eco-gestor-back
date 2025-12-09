package br.com.ecogestor.service;

import br.com.ecogestor.dto.request.LicencaAmbientalRequest;
import br.com.ecogestor.dto.request.PontoColetaRequest;
import br.com.ecogestor.dto.response.LicencaAmbientalResponse;
import br.com.ecogestor.dto.response.PontoColetaResponse;
import br.com.ecogestor.entidade.Endereco;
import br.com.ecogestor.entidade.LicencaAmbiental;
import br.com.ecogestor.entidade.PontoColeta;
import br.com.ecogestor.mapper.LicencaAmbientalMapper;
import br.com.ecogestor.repository.LicencaAmbientalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LicencaAmbientalService {

    @Autowired
    public LicencaAmbientalRepository licencaAmbientalRepository;
    @Autowired
    LicencaAmbientalMapper licencaAmbientalMapper;

    @Transactional
    public LicencaAmbientalResponse criar(LicencaAmbientalRequest request) {

        LicencaAmbiental licencaAmbiental = licencaAmbientalMapper.toEntity(request);

        licencaAmbiental.setDataInicio(LocalDateTime.now());

        return licencaAmbientalMapper.toResponse(licencaAmbientalRepository.save(licencaAmbiental));
    }
}
