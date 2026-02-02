package br.com.ecogestor.dashboard.controller;

import br.com.ecogestor.pontocoleta.service.PontoColetaService;
import br.com.ecogestor.residuos.service.ResiduosService;
import br.com.ecogestor.licenca.service.LicencaAmbientalService;
import br.com.ecogestor.licenca.dto.response.LicencaAmbientalResponse;
import br.com.ecogestor.atividade.service.AtividadeRecenteService;
import br.com.ecogestor.atividade.dto.response.AtividadeRecenteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private PontoColetaService pontoColetaService;

    @Autowired
    private ResiduosService residuosService;

    @Autowired
    private LicencaAmbientalService licencaAmbientalService;

    @Autowired
    private AtividadeRecenteService atividadeRecenteService;

    @GetMapping("/total-pontos-ativos")
    public ResponseEntity<Long> buscarTotalPontosAtivos() {
        log.info("Buscando total de pontos de coleta ativos");
        return ResponseEntity.ok(pontoColetaService.contarPontosColetaAtivos());
    }

    @GetMapping("/total-peso-mes")
    public ResponseEntity<Double> buscarTotalPesoMes() {
        log.info("Buscando total de peso de resíduos coletados no mês");
        return ResponseEntity.ok(residuosService.somarPesoMesAtual());
    }

    @GetMapping("/residuos-por-tipo")
    public ResponseEntity<Map<String, Double>> buscarResiduosPorTipo() {
        log.info("Buscando resíduos agrupados por tipo");
        return ResponseEntity.ok(residuosService.somarPesoPorTipo());
    }

    @GetMapping("/residuos-ultimos-meses")
    public ResponseEntity<List<Map<String, Object>>> buscarResiduosUltimosMeses() {
        log.info("Buscando resíduos dos últimos 6 meses");
        return ResponseEntity.ok(residuosService.somarPesoUltimosMeses(6));
    }

    @GetMapping("/licencas-proximas-vencer")
    public ResponseEntity<List<LicencaAmbientalResponse>> buscarLicencasProximasVencer() {
        log.info("Buscando licenças próximas do vencimento");
        return ResponseEntity.ok(licencaAmbientalService.buscarProximasVencer(5));
    }

    @GetMapping("/atividades")
    public ResponseEntity<List<AtividadeRecenteResponse>> buscarAtividadesRecentes(
            @RequestParam(defaultValue = "10") int limit) {
        log.info("Buscando {} atividades recentes", limit);
        return ResponseEntity.ok(atividadeRecenteService.buscarUltimas(limit));
    }
}
