package br.com.ecogestor.cooperativa.service;

import br.com.ecogestor.cooperativa.dto.request.CooperativaRequest;
import br.com.ecogestor.cooperativa.dto.response.CooperativaResponse;
import br.com.ecogestor.cooperativa.entity.Cooperativa;
import br.com.ecogestor.cooperativa.mapper.CooperativaMapper;
import br.com.ecogestor.cooperativa.repository.CooperativaRepository;
import br.com.ecogestor.endereco.entity.Endereco;
import br.com.ecogestor.endereco.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CooperativaService {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private CooperativaRepository cooperativaRepository;

    @Autowired
    private CooperativaMapper cooperativaMapper;

    @Transactional
    public CooperativaResponse criar(CooperativaRequest request) {

        Cooperativa cooperativa = cooperativaMapper.toEntity(request);
        Endereco endereco = enderecoService.buscarEnderecoPorId(request.getEnderecoId());
        cooperativa.setEndereco(endereco);
        cooperativa.setDataInicio(LocalDateTime.now());

        return cooperativaMapper.toResponse(cooperativaRepository.save(cooperativa));
    }

    @Transactional(readOnly = true)
    public List<CooperativaResponse> listarTodas() {
        return cooperativaRepository.buscaTodosRegistroAtivos()
                .stream()
                .map(cooperativaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<CooperativaResponse> buscarCooperativasPaginado(Pageable pageable) {
        return cooperativaRepository.buscaTodosRegistroAtivos(pageable).map(cooperativaMapper::toResponse);
    }

    @Transactional
    public CooperativaResponse editar(Long id, CooperativaRequest request) {
        if (id == null) {
            throw new IllegalArgumentException("ID da Cooperativa não pode ser nulo");
        }
        Cooperativa cooperativa = buscarPorId(id);
        cooperativaMapper.atualizar(cooperativa, request);
        if (request.getEnderecoId() != null) {
            Endereco endereco = enderecoService.buscarEnderecoPorId(request.getEnderecoId());
            cooperativa.setEndereco(endereco);
        }
        cooperativa = cooperativaRepository.save(cooperativa);
        return cooperativaMapper.toResponse(cooperativa);
    }

    @Transactional
    public CooperativaResponse remover(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID da Cooperativa não pode ser nulo");
        }
        Cooperativa cooperativa = buscarPorId(id);
        cooperativa.setDataFim(LocalDateTime.now());
        cooperativa = cooperativaRepository.save(cooperativa);
        return cooperativaMapper.toResponse(cooperativa);
    }

    @Transactional(readOnly = true)
    public Cooperativa buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID da Cooperativa não pode ser nulo");
        }
        return cooperativaRepository.buscaUmRegistroAtivo(id)
                .orElseThrow(() -> new RuntimeException("Cooperativa não encontrada ou já removida: id=" + id));
    }

    @Transactional(readOnly = true)
    public Page<CooperativaResponse> buscarComFiltros(CooperativaRequest filtro, Pageable pageable) {
        String nomeEmpresa = filtro != null ? filtro.getNomeEmpresa() : null;
        String cnpj = filtro != null ? filtro.getCnpj() : null;
        String enderecoNome = filtro != null ? filtro.getEnderecoNome() : null;

        return cooperativaRepository
                .buscarComFiltros(nomeEmpresa, cnpj, enderecoNome, pageable)
                .map(cooperativaMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public CooperativaResponse buscarPorIdResponse(Long id) {
        return cooperativaMapper.toResponse(buscarPorId(id));
    }

    @Transactional
    public Long buscarTotalCooperativasAtivas() {
        return cooperativaRepository.buscarTotalCooperativasAtivas();
    }
}



