package br.com.ecogestor.service;

import br.com.ecogestor.dto.request.PontoColetaRequest;
import br.com.ecogestor.dto.response.PontoColetaResponse;
import br.com.ecogestor.entidade.Endereco;
import br.com.ecogestor.entidade.PontoColeta;
import br.com.ecogestor.mapper.PontoColetaMapper;
import br.com.ecogestor.repository.EnderecoRepository;
import br.com.ecogestor.repository.PontoColetaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PontoColetaService {

    @Autowired
    public PontoColetaRepository pontoColetaRepository;

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

    @Transactional
    public PontoColetaResponse editar(Long id, PontoColetaRequest request) {
        PontoColeta pontoColeta = buscarPorId(id);
        pontoColetaMapper.atualizar(pontoColeta, request);
        if (request.getEnderecoId() != null) {
            Endereco endereco = buscarEnderecoPorId(request.getEnderecoId());
            pontoColeta.setEndereco(endereco);
        }
        pontoColeta.setDataFim(LocalDateTime.now());
        pontoColetaRepository.save(pontoColeta);
        return null;
    }

    public static PontoColetaResponse remover(Long id) {
        return null;
    }

    public PontoColeta buscarPorId(Long id) {
        return pontoColetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro ao procurar ponto de coleta por ID"));
    }

    public Endereco buscarEnderecoPorId(Long id) {
        return enderecoRepository.findEnderecoById(id);
    }

    public Page<PontoColetaResponse> buscarPontosColetaPaginado(Pageable pageable) {
        return pontoColetaRepository.findAll(pageable).map(pontoColetaMapper::toResponse);
    }
}
