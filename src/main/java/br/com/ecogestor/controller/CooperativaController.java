package br.com.ecogestor.controller;

import br.com.ecogestor.dto.request.CooperativaRequest;
import br.com.ecogestor.dto.response.CooperativaResponse;
import br.com.ecogestor.service.CooperativaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/cooperativas")
public class CooperativaController {

    @Autowired
    CooperativaService cooperativaService;

    @PostMapping(path = "/criar")
    public ResponseEntity<CooperativaResponse> criar(@RequestBody CooperativaRequest cooperativaRequest) {
        log.info("Cadastrando cooperativa ->");
        return ResponseEntity.ok(cooperativaService.criar(cooperativaRequest));
    }

    @GetMapping(path = "/busca/paginada")
    public Page<CooperativaResponse> buscarCooperativasPaginado(Pageable pageable) {
        log.info("Buscando cooperativas paginado ->");
        return cooperativaService.buscarCooperativasPaginado(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CooperativaResponse> buscarPorId(@PathVariable Long id) {
        log.info("Buscando cooperativa por ID: {}", id);
        return ResponseEntity.ok(cooperativaService.buscarPorIdResponse(id));
    }

    @PutMapping(path = "/editar/{id}")
    public ResponseEntity<CooperativaResponse> atualizar(@PathVariable Long id, @RequestBody CooperativaRequest request) {
        log.info("Atualizando cooperativa ID: {}", id);
        return ResponseEntity.ok(cooperativaService.editar(id, request));
    }

    @DeleteMapping(path = "/remove/{id}")
    public ResponseEntity<CooperativaResponse> remover(@PathVariable("id") Long id) {
        log.info("Excluindo cooperativa ->");
        return ResponseEntity.ok(cooperativaService.remover(id));
    }

    @PostMapping(path = "/busca/filtro")
    public Page<CooperativaResponse> buscarComFiltros(
            @RequestBody CooperativaRequest filtro,
            Pageable pageable) {
        log.info("Buscando cooperativas com filtros ->");
        return cooperativaService.buscarComFiltros(filtro, pageable);
    }
}


