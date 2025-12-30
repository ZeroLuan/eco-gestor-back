package br.com.ecogestor.repository;

import br.com.ecogestor.entidade.Residuos;
import br.com.ecogestor.enums.EnumTipoResiduo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ResiduosRepository extends JpaRepository<Residuos, Long>{

    @Query("SELECT r FROM Residuos r WHERE r.dataFim IS NULL")
    Page<Residuos> buscaTodosRegistroAtivos(@Param("pageable") Pageable pageable);

    @Query("SELECT r FROM Residuos r WHERE r.id = :id AND r.dataFim IS NULL")
    Optional<Residuos> buscaUmRegistroAtivo(@Param("id") Long id);

    @Query("SELECT r FROM Residuos r " +
            "LEFT JOIN r.pontoColeta p " +
            "WHERE r.dataFim IS NULL " +
            "AND (:tipoResiduo IS NULL OR r.tipoResiduo = :tipoResiduo) " +
            "AND (:nomeResponsavel IS NULL OR LOWER(r.nomeResponsavel) LIKE LOWER(CONCAT('%', :nomeResponsavel, '%'))) " +
            "AND (:dataColeta IS NULL OR r.dataColeta = :dataColeta) " +
            "AND (:pontoColetaId IS NULL OR p.id = :pontoColetaId)")
    Page<Residuos> buscarComFiltros(
            @Param("tipoResiduo") EnumTipoResiduo tipoResiduo,
            @Param("nomeResponsavel") String nomeResponsavel,
            @Param("dataColeta") LocalDate dataColeta,
            @Param("pontoColetaId") Long pontoColetaId,
            Pageable pageable);

}
