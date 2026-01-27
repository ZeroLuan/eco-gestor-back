package br.com.ecogestor.licenca.service;

import br.com.ecogestor.cooperativa.service.CooperativaService;
import br.com.ecogestor.cooperativa.entity.Cooperativa;
import br.com.ecogestor.licenca.dto.request.LicencaAmbientalRequest;
import br.com.ecogestor.licenca.dto.response.LicencaAmbientalResponse;
import br.com.ecogestor.licenca.entity.LicencaAmbiental;
import br.com.ecogestor.licenca.mapper.LicencaAmbientalMapper;
import br.com.ecogestor.licenca.repository.LicencaAmbientalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class LicencaAmbientalService {

    @Autowired
    private LicencaAmbientalRepository licencaAmbientalRepository;

    @Autowired
    private LicencaAmbientalMapper licencaAmbientalMapper;

    @Autowired
    private CooperativaService cooperativaService;

    @Transactional
    public LicencaAmbientalResponse criar(LicencaAmbientalRequest request) {
        LicencaAmbiental licencaAmbiental = licencaAmbientalMapper.toEntity(request);

        if (request.getCooperativaId() != null) {
            Cooperativa cooperativa = cooperativaService.buscarPorId(request.getCooperativaId());
            licencaAmbiental.setCooperativa(cooperativa);
            licencaAmbiental.setEmpresa(cooperativa);
        }

        licencaAmbiental.setDataInicio(LocalDateTime.now());

        return licencaAmbientalMapper.toResponse(licencaAmbientalRepository.save(licencaAmbiental));
    }

    @Transactional(readOnly = true)
    public Page<LicencaAmbientalResponse> buscarPaginado(Pageable pageable) {
        return licencaAmbientalRepository.buscaTodosRegistroAtivos(pageable).map(licencaAmbientalMapper::toResponse);
    }

    @Transactional
    public LicencaAmbientalResponse editar(Long id, LicencaAmbientalRequest request) {
        LicencaAmbiental licencaAmbiental = buscarPorId(id);
        licencaAmbientalMapper.atualizar(licencaAmbiental, request);

        if (request.getCooperativaId() != null) {
            Cooperativa cooperativa = cooperativaService.buscarPorId(request.getCooperativaId());
            licencaAmbiental.setCooperativa(cooperativa);
            licencaAmbiental.setEmpresa(cooperativa);
        }

        return licencaAmbientalMapper.toResponse(licencaAmbientalRepository.save(licencaAmbiental));
    }

    @Transactional
    public LicencaAmbientalResponse remover(Long id) {
        LicencaAmbiental licencaAmbiental = buscarPorId(id);
        licencaAmbiental.setDataFim(LocalDateTime.now());
        return licencaAmbientalMapper.toResponse(licencaAmbientalRepository.save(licencaAmbiental));
    }

    @Transactional(readOnly = true)
    public LicencaAmbiental buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID da Licença Ambiental não pode ser nulo");
        }
        return licencaAmbientalRepository.buscaUmRegistroAtivo(id)
                .orElseThrow(() -> new RuntimeException("Licença Ambiental não encontrada ou já removida: id=" + id));
    }

    @Transactional(readOnly = true)
    public LicencaAmbientalResponse buscarPorIdResponse(Long id) {
        return licencaAmbientalMapper.toResponse(buscarPorId(id));
    }

    @Transactional(readOnly = true)
    public Page<LicencaAmbientalResponse> buscarComFiltros(LicencaAmbientalRequest filtro, Pageable pageable) {
        String numeroLicenca = filtro != null ? filtro.getNumeroLicenca() : null;
        Long cooperativaId = filtro != null ? filtro.getCooperativaId() : null;

        return licencaAmbientalRepository
                .buscarComFiltros(numeroLicenca, cooperativaId, pageable)
                .map(licencaAmbientalMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Long buscarTotalLicencasAtivas() {
        return licencaAmbientalRepository.buscarTotalLicencas();
    }
}
