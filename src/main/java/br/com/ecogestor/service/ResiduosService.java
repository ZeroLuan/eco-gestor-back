package br.com.ecogestor.service;
import br.com.ecogestor.dto.request.ResiduosRequest;
import br.com.ecogestor.dto.response.ResiduosResponse;
import br.com.ecogestor.entidade.Residuos;
import br.com.ecogestor.mapper.ResiduosMapper;
import br.com.ecogestor.repository.ResiduosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResiduosService {

    @Autowired
    public ResiduosRepository residuosRepository;

    @Autowired
    ResiduosMapper residuosMapper;

    @Transactional
    public ResiduosResponse criar( ResiduosRequest request) {

        Residuos  residuos = residuosMapper.toEntity(request);

        return residuosMapper.toResponse(residuosRepository.save(residuos));
    }
}



