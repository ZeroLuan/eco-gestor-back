package br.com.ecogestor.cooperativa.controller;

import br.com.ecogestor.cooperativa.dto.request.CooperativaRequest;
import br.com.ecogestor.cooperativa.dto.response.CooperativaResponse;
import br.com.ecogestor.cooperativa.service.CooperativaService;
import br.com.ecogestor.atividade.service.AtividadeRecenteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cooperativas")
public class CooperativaController {

    @Autowired
    CooperativaService cooperativaService;

    @Autowired
    AtividadeRecenteService atividadeRecenteService;

    @PostMapping(path = "/criar")
    public ResponseEntity<CooperativaResponse> criar(@RequestBody CooperativaRequest cooperativaRequest) {
        log.info("Cadastrando cooperativa ->");
        CooperativaResponse response = cooperativaService.criar(cooperativaRequest);
        atividadeRecenteService.registrarCriacaoCooperativa(response.getId(), response.getNome());
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/listar-todas")
    public ResponseEntity<List<CooperativaResponse>> listarTodas() {
        log.info("Listando todas as cooperativas ->");
        return ResponseEntity.ok(cooperativaService.listarTodas());
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

    @GetMapping(path = "busca-total-cooperativas-ativas")
    public ResponseEntity<Long> buscarTotalCooperativasAtivas(){
        log.info("Buscando o total de cooperativas ativas ->");
        return ResponseEntity.ok(cooperativaService.buscarTotalCooperativasAtivas());
    }
}


