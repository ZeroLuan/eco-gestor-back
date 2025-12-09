package br.com.ecogestor.controller;

import br.com.ecogestor.dto.request.PontoColetaRequest;
import br.com.ecogestor.dto.request.ResiduosRequest;
import br.com.ecogestor.dto.response.PontoColetaResponse;
import br.com.ecogestor.dto.response.ResiduosResponse;

import br.com.ecogestor.service.ResiduosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/residuos")
public class ResiduosController {

    @Autowired
    ResiduosService residuosService;

    @PostMapping(path = "/criar")
    public ResponseEntity<ResiduosResponse> criar(@RequestBody ResiduosRequest residuosRequest) {
        log.info("Registrando residuos.");
        return ResponseEntity.ok(residuosService.criar(residuosRequest));
    }


}
