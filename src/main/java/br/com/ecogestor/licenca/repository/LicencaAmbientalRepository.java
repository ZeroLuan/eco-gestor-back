package br.com.ecogestor.licenca.repository;

import br.com.ecogestor.licenca.entity.LicencaAmbiental;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LicencaAmbientalRepository extends JpaRepository<LicencaAmbiental, Long> {

    @Query("SELECT l FROM LicencaAmbiental l WHERE l.dataFim IS NULL")
    Page<LicencaAmbiental> buscaTodosRegistroAtivos(Pageable pageable);

    @Query("SELECT l FROM LicencaAmbiental l WHERE l.id = :id AND l.dataFim IS NULL")
    Optional<LicencaAmbiental> buscaUmRegistroAtivo(@Param("id") Long id);

    @Query("SELECT l FROM LicencaAmbiental l " +
            "WHERE l.dataFim IS NULL " +
            "AND (:numeroLicenca IS NULL OR LOWER(l.numeroLicenca) LIKE LOWER(CONCAT('%', :numeroLicenca, '%'))) " +
            "AND (:cooperativaId IS NULL OR l.cooperativa.id = :cooperativaId)")
    Page<LicencaAmbiental> buscarComFiltros(
            @Param("numeroLicenca") String numeroLicenca,
            @Param("cooperativaId") Long cooperativaId,
            Pageable pageable);

    @Query("SELECT COUNT(l) FROM LicencaAmbiental l WHERE l.dataFim IS NULL")
    Long buscarTotalLicencas();
}
