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

        Endereco endereco = enderecoRepository.findById(request.getEnderecoId())
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        pontoColeta.setEndereco(endereco);
        pontoColeta.setDataInicio(LocalDateTime.now());

        return pontoColetaMapper.toResponse(pontoColetaRepository.save(pontoColeta));
    }
}
