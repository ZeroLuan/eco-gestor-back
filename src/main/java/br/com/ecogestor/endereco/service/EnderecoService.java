package br.com.ecogestor.endereco.service;

import br.com.ecogestor.endereco.dto.request.EnderecoRequest;
import br.com.ecogestor.endereco.dto.response.EnderecoResponse;
import br.com.ecogestor.endereco.entity.Endereco;
import br.com.ecogestor.endereco.mapper.EnderecoMapper;
import br.com.ecogestor.endereco.repository.EnderecoRepository;
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

    @Transactional
    public Endereco buscarEnderecoPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do Ponto de Coleta não pode ser nulo");
        }
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro ao procurar Endereço"));
    }

}
