package br.com.ecogestor.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/ponto-coleta")
public class PontoColetaController {


    @PostMapping(path = "/criar")
    public ResponseEntity<?> criar() {
        log.info("Criando ponto de coleta");
        return ResponseEntity.ok("ok");
    }

}
