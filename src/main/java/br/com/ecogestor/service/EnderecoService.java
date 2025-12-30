package br.com.ecogestor.service;

import br.com.ecogestor.dto.request.EnderecoRequest;
import br.com.ecogestor.dto.response.EnderecoResponse;
import br.com.ecogestor.entidade.Endereco;
import br.com.ecogestor.mapper.EnderecoMapper;
import br.com.ecogestor.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoMapper enderecoMapper;

    @Transactional
    public EnderecoResponse criarEndereco(EnderecoRequest request) {
        Endereco endereco = enderecoMapper.toEntity(request);
        endereco.setDataInicio(LocalDateTime.now());
        return enderecoMapper.toResponse(enderecoRepository.save(endereco));
    }

    @Transactional
    public EnderecoResponse editarEndereco(Long id, EnderecoRequest request) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        enderecoMapper.atualizar(endereco, request);
        return enderecoMapper.toResponse(enderecoRepository.save(endereco));
    }


}
