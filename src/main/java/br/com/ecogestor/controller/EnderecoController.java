package br.com.ecogestor.controller;

import br.com.ecogestor.dto.request.EnderecoRequest;
import br.com.ecogestor.dto.response.EnderecoResponse;
import br.com.ecogestor.service.EnderecoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

    @Autowired
    EnderecoService enderecoService;

    @PostMapping(path = "/criar")
    public ResponseEntity<EnderecoResponse> criarEndereco(@RequestBody EnderecoRequest enderecoRequest) {
        log.info("Criando um novo endereço");
        return ResponseEntity.ok(enderecoService.criarEndereco(enderecoRequest));
    }




}
