package br.com.ecogestor.pontocoleta.service;

import br.com.ecogestor.cooperativa.service.CooperativaService;
import br.com.ecogestor.endereco.entity.Endereco;
import br.com.ecogestor.endereco.service.EnderecoService;
import br.com.ecogestor.pontocoleta.dto.request.PontoColetaRequest;
import br.com.ecogestor.pontocoleta.dto.response.PontoColetaResponse;
import br.com.ecogestor.pontocoleta.entity.PontoColeta;
import br.com.ecogestor.pontocoleta.mapper.PontoColetaMapper;
import br.com.ecogestor.pontocoleta.repository.PontoColetaRepository;
import br.com.ecogestor.shared.enums.EnumTipoResiduo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PontoColetaService {

    @Autowired
    private PontoColetaRepository pontoColetaRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private CooperativaService cooperativaService;

    @Autowired
    private PontoColetaMapper pontoColetaMapper;

    @Transactional
    public PontoColetaResponse criar(PontoColetaRequest request) {
        PontoColeta pontoColeta = pontoColetaMapper.toEntity(request);
        Endereco endereco = enderecoService.buscarEnderecoPorId(request.getEnderecoId());
        pontoColeta.setEndereco(endereco);

        if (request.getCooperativaId() != null) {
            pontoColeta.setCooperativa(cooperativaService.buscarPorId(request.getCooperativaId()));
        }

        pontoColeta.setDataInicio(LocalDateTime.now());
        return pontoColetaMapper.toResponse(pontoColetaRepository.save(pontoColeta));
    }

    @Transactional(readOnly = true)
    public Page<PontoColetaResponse> buscarPontosColetaPaginado(Pageable pageable) {
        return pontoColetaRepository.buscaTodosRegistroAtivos(pageable).map(pontoColetaMapper::toResponse);
    }

    @Transactional
    public PontoColetaResponse editar(Long id, PontoColetaRequest request) {
        if (id == null) {
            throw new IllegalArgumentException("ID do Ponto de Coleta não pode ser nulo");
        }
        PontoColeta pontoColeta = buscarPorId(id);
        pontoColetaMapper.atualizar(pontoColeta, request);
        if (request.getEnderecoId() != null) {
            Endereco endereco = enderecoService.buscarEnderecoPorId(request.getEnderecoId());
            pontoColeta.setEndereco(endereco);
        }
        if (request.getCooperativaId() != null) {
            pontoColeta.setCooperativa(cooperativaService.buscarPorId(request.getCooperativaId()));
        }
        pontoColeta = pontoColetaRepository.save(pontoColeta);
        return pontoColetaMapper.toResponse(pontoColeta);
    }

    @Transactional
    public PontoColetaResponse remover(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do Ponto de Coleta não pode ser nulo");
        }
        PontoColeta pontoColeta = buscarPorId(id);
        pontoColeta.setDataFim(LocalDateTime.now());
        pontoColeta = pontoColetaRepository.save(pontoColeta);
        return pontoColetaMapper.toResponse(pontoColeta);
    }

    @Transactional(readOnly = true)
    public PontoColeta buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do Ponto de Coleta não pode ser nulo");
        }
        return pontoColetaRepository.buscaUmRegistroAtivo(id).orElseThrow(() -> new RuntimeException("Ponto de Coleta não encontrado ou já removido: id=" + id));
    }

    @Transactional(readOnly = true)
    public Page<PontoColetaResponse> buscarComFiltros(PontoColetaRequest filtro, Pageable pageable) {
        String tipoResiduo = filtro != null && filtro.getTipoResiduo() != null
                ? filtro.getTipoResiduo().toString()
                : null;

        String nomePonto = filtro != null ? filtro.getNomePonto() : null;
        String enderecoNome = filtro != null ? filtro.getEnderecoNome() : null;

        return pontoColetaRepository
                .buscarComFiltros(nomePonto, tipoResiduo, enderecoNome, pageable)
                .map(pontoColetaMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public List<PontoColetaResponse> buscarPorTipoResiduo(EnumTipoResiduo tipoResiduo) {
        if(tipoResiduo == null){
            throw new IllegalArgumentException("O tipo de Resíduo é nullo");
        }
        return pontoColetaRepository.buscarPorTipoResiduo(tipoResiduo).stream().map(pontoColetaMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public long contarPontosColetaAtivos() {
        return pontoColetaRepository.contarTodosRegistrosAtivos();
    }
}
