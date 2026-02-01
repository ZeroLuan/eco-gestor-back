package br.com.ecogestor.residuos.service;
import br.com.ecogestor.pontocoleta.entity.PontoColeta;
import br.com.ecogestor.pontocoleta.service.PontoColetaService;
import br.com.ecogestor.residuos.dto.request.ResiduosRequest;
import br.com.ecogestor.residuos.dto.response.ResiduosResponse;
import br.com.ecogestor.residuos.entity.Residuos;
import br.com.ecogestor.residuos.mapper.ResiduosMapper;
import br.com.ecogestor.residuos.repository.ResiduosRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

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
                        filtro != null ? filtro.getDataInicio() : null,
                        filtro != null ? filtro.getDataFim() : null,
                        filtro != null ? filtro.getLocal() : null,
                        filtro != null ? filtro.getIdPontoColeta() : null,
                        pageable)
                .map(residuosMapper::toResponse);
    }

    @Transactional
    public ResiduosResponse buscarResiduoResponsePorId(Long id) {
        Residuos residuos = buscarPorId(id);
        return residuosMapper.toResponse(residuos);
    }

    @Transactional
    public Double somarPesoMesAtual() {
        LocalDate hoje = LocalDate.now();
        Double total = residuosRepository.somarPesoPorMesEAno(hoje.getMonthValue(), hoje.getYear());
        return total != null ? total : 0.0;
    }

    @Transactional
    public Map<String, Double> somarPesoPorTipo() {
        List<Object[]> resultados = residuosRepository.somarPesoPorTipo();
        Map<String, Double> mapa = new HashMap<>();
        
        for (Object[] resultado : resultados) {
            String tipo = resultado[0] != null ? resultado[0].toString() : "DESCONHECIDO";
            Double peso = resultado[1] != null ? ((Number) resultado[1]).doubleValue() : 0.0;
            mapa.put(tipo, peso);
        }
        
        return mapa;
    }

    @Transactional
    public List<Map<String, Object>> somarPesoUltimosMeses(int meses) {
        LocalDate dataInicio = LocalDate.now().minusMonths(meses);
        List<Object[]> resultados = residuosRepository.somarPesoPorMesUltimos(dataInicio);
        List<Map<String, Object>> lista = new ArrayList<>();
        
        for (Object[] resultado : resultados) {
            Map<String, Object> item = new HashMap<>();
            item.put("mes", resultado[0]);
            item.put("ano", resultado[1]);
            item.put("peso", resultado[2] != null ? ((Number) resultado[2]).doubleValue() : 0.0);
            lista.add(item);
        }
        
        return lista;
    }
}
