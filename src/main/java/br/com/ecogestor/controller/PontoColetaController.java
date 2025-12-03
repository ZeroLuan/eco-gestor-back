package br.com.ecogestor.controller;

import br.com.ecogestor.dto.request.PontoColetaRequest;
import br.com.ecogestor.dto.response.PontoColetaResponse;
import br.com.ecogestor.service.PontoColetaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/ponto-coleta")
public class PontoColetaController {

    @Autowired
    PontoColetaService pontoColetaService;

    @PostMapping(path = "/criar")
    public ResponseEntity<PontoColetaResponse> criar(
            @RequestBody PontoColetaRequest pontoColetaRequest) {
        log.info("Criando ponto de coleta ->");
        return ResponseEntity.ok(pontoColetaService.criar(pontoColetaRequest));
    }

    @PutMapping(path = "/editar/{id}")
    public ResponseEntity<PontoColetaResponse> editar(
            @PathVariable("id") Long id,
            @RequestBody PontoColetaRequest request) {
        log.info("Editando ponto de coleta ->");
        return ResponseEntity.ok(pontoColetaService.editar(id, request));
    }

    @DeleteMapping(path = "remove/{id}")
    public ResponseEntity<PontoColetaResponse> remover(@PathVariable("id") Long id) {
        log.info("Excluindo ponto de coleta ->");
        return ResponseEntity.ok(PontoColetaService.remover(id));
    }

    @GetMapping(path = "/busca/paginada")
    public Page<PontoColetaResponse> buscarPontosColetaPaginado(Pageable peageable){
        log.info("Buscando ponto de coleta paginado ->");
        return pontoColetaService.buscarPontosColetaPaginado(peageable);
    }

}
