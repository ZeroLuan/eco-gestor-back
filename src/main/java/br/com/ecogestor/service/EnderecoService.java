package br.com.ecogestor.service;

import br.com.ecogestor.dto.request.EnderecoRequest;
import br.com.ecogestor.dto.response.EnderecoResponse;
import br.com.ecogestor.entidade.Endereco;
import br.com.ecogestor.mapper.EnderecoMapper;
import br.com.ecogestor.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    EnderecoMapper enderecoMapper;

    public EnderecoResponse criarEndereco(EnderecoRequest enderecoRequest) {

        Endereco endereco = enderecoMapper.toEntity(enderecoRequest);

        return enderecoMapper.toResponse(enderecoRepository.save(endereco));
    }


}
