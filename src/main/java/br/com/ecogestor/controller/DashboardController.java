package br.com.ecogestor.controller;

import br.com.ecogestor.service.PontoColetaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private PontoColetaService pontoColetaService;

    @GetMapping("/total-pontos-ativos")
    public ResponseEntity<Long> buscarTotalPontosAtivos() {
        log.info("Buscando total de pontos de coleta ativos");
        return ResponseEntity.ok(pontoColetaService.contarPontosColetaAtivos());
    }

}
