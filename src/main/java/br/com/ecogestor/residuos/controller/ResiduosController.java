package br.com.ecogestor.residuos.controller;

import br.com.ecogestor.residuos.dto.request.ResiduosRequest;
import br.com.ecogestor.residuos.dto.response.ResiduosResponse;
import br.com.ecogestor.residuos.service.ResiduosService;
import br.com.ecogestor.atividade.service.AtividadeRecenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/residuos")
public class ResiduosController {

    @Autowired
    ResiduosService residuosService;

    @Autowired
    AtividadeRecenteService atividadeRecenteService;

    @PostMapping(path = "/criar")
    public ResponseEntity<ResiduosResponse> criar(@RequestBody ResiduosRequest residuosRequest) {
        log.info("Criando resíduos ->");
        ResiduosResponse response = residuosService.criar(residuosRequest);
        atividadeRecenteService.registrarCriacaoResiduo(response.getId(), response.getPeso(), String.valueOf(response.getTipoResiduo()));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResiduosResponse> buscarPorId(@PathVariable Long id) {
        log.info("Buscando resíduo por ID ->");
        return ResponseEntity.ok(residuosService.buscarResiduoResponsePorId(id));
    }

    @GetMapping(path = "/busca/paginada")
    public Page<ResiduosResponse> buscarResiduosPaginado(Pageable peageable){
        log.info("Buscando resíduos paginados ->");
        return residuosService.buscarResiduosPaginado(peageable);
    }

    @PutMapping(path = "/editar/{id}")
    public ResponseEntity<ResiduosResponse> editar(
            @PathVariable("id") Long id,
            @RequestBody ResiduosRequest request) {
        log.info("Editando resíduos ->");
        return ResponseEntity.ok(residuosService.editar(id, request));
    }

    @DeleteMapping(path = "/remove/{id}")
    public ResponseEntity<ResiduosResponse> remover(@PathVariable("id") Long id) {
        log.info("Excluindo resíduos ->");
        return ResponseEntity.ok(residuosService.remover(id));
    }

    @PostMapping(path = "/busca/filtro")
    public Page<ResiduosResponse> buscarComFiltros(
            @RequestBody ResiduosRequest filtro,
            Pageable pageable) {
        log.info("Buscando resíduos com filtros ->");
        return residuosService.buscarComFiltros(filtro, pageable);
    }

}
