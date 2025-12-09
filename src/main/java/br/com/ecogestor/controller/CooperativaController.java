package br.com.ecogestor.controller;


import br.com.ecogestor.dto.request.CooperativaRequest;
import br.com.ecogestor.dto.request.PontoColetaRequest;
import br.com.ecogestor.dto.response.CooperativaResponse;

import br.com.ecogestor.dto.response.PontoColetaResponse;
import br.com.ecogestor.service.CooperativaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cooperativas")
public class CooperativaController {


    @Autowired
    CooperativaService cooperativaService;

    @PostMapping//(path = "/criar")
    public ResponseEntity<CooperativaResponse> criar(@RequestBody CooperativaRequest cooperativaRequest) {
        log.info("Cadastrando cooperativa.");
        return ResponseEntity.ok(cooperativaService.criar(cooperativaRequest));
    }

  /*  @GetMapping
    public ResponseEntity<List<CooperativaResponse>> listarTodas() {
        log.info("Listando cooperativas.");
        return ResponseEntity.ok(cooperativaService.listarTodas());
    }
    /*
    @GetMapping("/{id}")
    public ResponseEntity<CooperativaResponse> buscarPorId(@PathVariable Long id) {
        log.info("Buscando cooperativa por ID: {}", id);
        return ResponseEntity.ok(cooperativaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CooperativaResponse> atualizar(@PathVariable Long id, @RequestBody CooperativaRequest request) {
        log.info("Atualizando cooperativa ID: {}", id);
        return ResponseEntity.ok(cooperativaService.atualizar(id, request));
    }*/
}


