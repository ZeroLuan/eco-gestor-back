package br.com.ecogestor.service;
import br.com.ecogestor.dto.request.ResiduosRequest;
import br.com.ecogestor.dto.response.ResiduosResponse;
import br.com.ecogestor.entidade.PontoColeta;
import br.com.ecogestor.entidade.Residuos;
import br.com.ecogestor.mapper.ResiduosMapper;
import br.com.ecogestor.repository.ResiduosRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ResiduosService {

    @Autowired
    private ResiduosRepository residuosRepository;

    @Autowired
    private ResiduosMapper residuosMapper;

    @Autowired
    private PontoColetaService pontoColetaService;

    @Transactional
    public ResiduosResponse criar(ResiduosRequest request) {
        Residuos residuos = residuosMapper.toEntity(request);
        PontoColeta pontoColeta = pontoColetaService.buscarPorId(request.getIdPontoColeta());
        residuos.setPontoColeta(pontoColeta);
        residuos.setDataInicio(LocalDateTime.now());
        return residuosMapper.toResponse(residuosRepository.save(residuos));
    }

    @Transactional
    public ResiduosResponse editar(Long id, ResiduosRequest request) {
        if (id == null) {
            throw new IllegalArgumentException("ID do Resíduo não pode ser nulo");
        }
        Residuos residuos = buscarPorId(id);
        residuosMapper.atualizar(residuos, request);
        if (request.getIdPontoColeta() != null) {
            PontoColeta pontoColeta = pontoColetaService.buscarPorId(request.getIdPontoColeta());
            residuos.setPontoColeta(pontoColeta);
        }
        residuos = residuosRepository.save(residuos);
        return residuosMapper.toResponse(residuos);
    }

    @Transactional
    public ResiduosResponse remover(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do Resíduo não pode ser nulo");
        }
        Residuos residuos = buscarPorId(id);
        residuos.setDataFim(LocalDateTime.now());
        residuos = residuosRepository.save(residuos);
        return residuosMapper.toResponse(residuos);
    }

    @Transactional
    public Residuos buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do Resíduo não pode ser nulo");
        }
        return residuosRepository.buscaUmRegistroAtivo(id)
                .orElseThrow(() -> new RuntimeException("Resíduo não encontrado ou já removido: id=" + id));
    }

    @Transactional
    public Page<ResiduosResponse> buscarResiduosPaginado(Pageable pageable) {
        return residuosRepository.buscaTodosRegistroAtivos(pageable).map(residuosMapper::toResponse);
    }

    @Transactional
    public Page<ResiduosResponse> buscarComFiltros(ResiduosRequest filtro, Pageable pageable) {
        return residuosRepository
                .buscarComFiltros(
                        filtro != null ? filtro.getTipoResiduo() : null,
                        filtro != null ? filtro.getNomeResponsavel() : null,
                        filtro != null ? filtro.getDataColeta() : null,
                        filtro != null ? filtro.getIdPontoColeta() : null,
                        pageable)
                .map(residuosMapper::toResponse);
    }
}



