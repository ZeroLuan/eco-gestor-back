package br.com.ecogestor.controller;


import br.com.ecogestor.dto.request.LicencaAmbientalRequest;
import br.com.ecogestor.dto.response.LicencaAmbientalResponse;
import br.com.ecogestor.entidade.LicencaAmbiental;
import br.com.ecogestor.service.LicencaAmbientalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/licenca-ambiental")
public class LicencaAmbientalController {

    @Autowired
    LicencaAmbientalService licencaAmbientalService;

    @PostMapping(path = "/criar")
    public ResponseEntity<LicencaAmbientalResponse> criar(@RequestBody LicencaAmbientalRequest licencaAmbientalRequest) {
        log.info("Criando ponto de coleta");
        return ResponseEntity.ok(licencaAmbientalService.criar(licencaAmbientalRequest));
    }


}
