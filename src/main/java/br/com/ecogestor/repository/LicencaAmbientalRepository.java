package br.com.ecogestor.repository;

import br.com.ecogestor.entidade.LicencaAmbiental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicencaAmbientalRepository extends JpaRepository<LicencaAmbiental, Long> {
}
