package br.com.ecogestor.service;

import br.com.ecogestor.dto.request.CooperativaRequest;
import br.com.ecogestor.dto.response.CooperativaResponse;
import br.com.ecogestor.entidade.Cooperativa;
import br.com.ecogestor.mapper.CooperativaMapper;
import br.com.ecogestor.repository.CooperativaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CooperativaService {


    @Autowired
    private CooperativaRepository cooperativaRepository;

    @Autowired
    private CooperativaMapper cooperativaMapper;

    @Transactional
    public CooperativaResponse criar(CooperativaRequest request) {

        Cooperativa cooperativa = cooperativaMapper.toEntity(request);

     //   cooperativa.setDataInicio(LocalDateTime.now());

        return cooperativaMapper.toResponse(cooperativaRepository.save(cooperativa));
    }
}



