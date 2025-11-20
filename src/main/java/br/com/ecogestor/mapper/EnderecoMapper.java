package br.com.ecogestor.mapper;

import br.com.ecogestor.dto.request.EnderecoRequest;
import br.com.ecogestor.dto.response.EnderecoResponse;
import br.com.ecogestor.entidade.Endereco;
import br.com.ecogestor.enums.EnumEstados;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EnderecoMapper {

    public Endereco toEntity(EnderecoRequest request) {
        Endereco endereco = new Endereco();
        endereco.setBairro(request.getBairro());
        endereco.setCep(request.getCep());
        endereco.setCidade(request.getCidade());
        endereco.setComplemento(request.getComplemento());
        endereco.setEstado(EnumEstados.valueOf(request.getEstado())); // converte String → Enum
        endereco.setLogradouro(request.getLogradouro());
        endereco.setNumero(request.getNumero());
        endereco.setDataInicio(LocalDateTime.now());
        return endereco;
    }

    public EnderecoResponse toResponse(Endereco entity) {
        EnderecoResponse response = new EnderecoResponse();

        response.setId(entity.getId());
        response.setBairro(entity.getBairro());
        response.setCep(entity.getCep());
        response.setCidade(entity.getCidade());
        response.setComplemento(entity.getComplemento());
        response.setEstado(entity.getEstado().getValue());
        response.setLogradouro(entity.getLogradouro());
        response.setNumero(entity.getNumero());
        response.setDataInicio(entity.getDataInicio());
        response.setDataFim(entity.getDataFim());

        return response;
    }


}
