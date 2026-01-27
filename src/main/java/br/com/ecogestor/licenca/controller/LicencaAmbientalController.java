package br.com.ecogestor.licenca.controller;


import br.com.ecogestor.licenca.service.LicencaAmbientalService;
import br.com.ecogestor.licenca.dto.request.LicencaAmbientalRequest;
import br.com.ecogestor.licenca.dto.response.LicencaAmbientalResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/licenca-ambiental")
public class LicencaAmbientalController {

    @Autowired
    LicencaAmbientalService licencaAmbientalService;

    @PostMapping(path = "/criar")
    public ResponseEntity<LicencaAmbientalResponse> criar(@RequestBody LicencaAmbientalRequest licencaAmbientalRequest) {
        log.info("Criando licença ambiental ->");
        return ResponseEntity.ok(licencaAmbientalService.criar(licencaAmbientalRequest));
    }

    @GetMapping(path = "/busca/paginada")
    public Page<LicencaAmbientalResponse> buscarPaginado(Pageable pageable) {
        log.info("Buscando licenças ambientais paginado ->");
        return licencaAmbientalService.buscarPaginado(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LicencaAmbientalResponse> buscarPorId(@PathVariable Long id) {
        log.info("Buscando licença ambiental por ID: {}", id);
        return ResponseEntity.ok(licencaAmbientalService.buscarPorIdResponse(id));
    }

    @PutMapping(path = "/editar/{id}")
    public ResponseEntity<LicencaAmbientalResponse> atualizar(@PathVariable Long id, @RequestBody LicencaAmbientalRequest request) {
        log.info("Atualizando licença ambiental ID: {}", id);
        return ResponseEntity.ok(licencaAmbientalService.editar(id, request));
    }

    @DeleteMapping(path = "/remove/{id}")
    public ResponseEntity<LicencaAmbientalResponse> remover(@PathVariable("id") Long id) {
        log.info("Excluindo licença ambiental ->");
        return ResponseEntity.ok(licencaAmbientalService.remover(id));
    }

    @PostMapping(path = "/busca/filtro")
    public Page<LicencaAmbientalResponse> buscarComFiltros(
            @RequestBody LicencaAmbientalRequest filtro,
            Pageable pageable) {
        log.info("Buscando licenças ambientais com filtros ->");
        return licencaAmbientalService.buscarComFiltros(filtro, pageable);
    }

    @GetMapping(path = "/buscar/total-licencas-ativas")
    public ResponseEntity<Long> buscarTotalLicencasAtivas(){
        log.info("Buscando total de licenças ativas");
        return ResponseEntity.ok(licencaAmbientalService.buscarTotalLicencasAtivas());
    }
}
