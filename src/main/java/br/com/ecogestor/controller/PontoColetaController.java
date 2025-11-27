package br.com.ecogestor.controller;

import br.com.ecogestor.dto.request.PontoColetaRequest;
import br.com.ecogestor.dto.response.PontoColetaResponse;
import br.com.ecogestor.service.PontoColetaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/ponto-coleta")
public class PontoColetaController {

    @Autowired
    PontoColetaService pontoColetaService;

    @PostMapping(path = "/criar")
    public ResponseEntity<PontoColetaResponse> criar(@RequestBody PontoColetaRequest pontoColetaRequest) {
        log.info("Criando ponto de coleta");
        return ResponseEntity.ok(pontoColetaService.criar(pontoColetaRequest));
    }

}
