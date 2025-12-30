package br.com.ecogestor.controller;

import br.com.ecogestor.dto.request.EnderecoRequest;
import br.com.ecogestor.dto.response.EnderecoResponse;
import br.com.ecogestor.service.EnderecoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

    @Autowired
    EnderecoService enderecoService;

    @PostMapping(path = "/criar")
    public ResponseEntity<EnderecoResponse> criarEndereco(
            @RequestBody EnderecoRequest request) {
        log.info("Criando um novo endereço ->");
        return ResponseEntity.ok(enderecoService.criarEndereco(request));
    }

    @PutMapping(path = "/editar/{id}")
    public ResponseEntity<EnderecoResponse> editarEndereco(
            @PathVariable("id") Long id,
            @RequestBody EnderecoRequest request) {
        log.info("Editando um endereço ->");
        return ResponseEntity.ok(enderecoService.editarEndereco(id, request));
    }




}
