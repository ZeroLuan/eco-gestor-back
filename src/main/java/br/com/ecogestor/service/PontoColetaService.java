package br.com.ecogestor.service;

import br.com.ecogestor.dto.request.PontoColetaRequest;
import br.com.ecogestor.dto.response.PontoColetaResponse;
import br.com.ecogestor.entidade.Endereco;
import br.com.ecogestor.entidade.PontoColeta;
import br.com.ecogestor.mapper.PontoColetaMapper;
import br.com.ecogestor.repository.EnderecoRepository;
import br.com.ecogestor.repository.PontoColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PontoColetaService {

    @Autowired
    PontoColetaRepository pontoColetaRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    PontoColetaMapper pontoColetaMapper;

    @Transactional
    public PontoColetaResponse criar(PontoColetaRequest request) {
        PontoColeta pontoColeta = pontoColetaMapper.toEntity(request);
        Endereco endereco = buscarEnderecoPorId(request.getEnderecoId());
        pontoColeta.setEndereco(endereco);
        pontoColeta.setDataInicio(LocalDateTime.now());
        return pontoColetaMapper.toResponse(pontoColetaRepository.save(pontoColeta));
    }

    @Transactional(readOnly = true)
    public Page<PontoColetaResponse> buscarPontosColetaPaginado(Pageable pageable) {
        return pontoColetaRepository.buscaTodosRegistroAtivos(pageable).map(pontoColetaMapper::toResponse);
    }

    @Transactional
    public PontoColetaResponse editar(Long id, PontoColetaRequest request) {
        PontoColeta pontoColeta = buscarPorId(id);
        pontoColetaMapper.atualizar(pontoColeta, request);
        if (request.getEnderecoId() != null) {
            Endereco endereco = buscarEnderecoPorId(request.getEnderecoId());
            pontoColeta.setEndereco(endereco);
        }
        pontoColeta = pontoColetaRepository.save(pontoColeta);
        return pontoColetaMapper.toResponse(pontoColeta);
    }

    @Transactional
    public PontoColetaResponse remover(Long id) {
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
    public Endereco buscarEnderecoPorId(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro ao procurar Endereço"));
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

}
