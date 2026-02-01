package br.com.ecogestor.residuos.repository;

import br.com.ecogestor.residuos.entity.Residuos;
import br.com.ecogestor.shared.enums.EnumTipoResiduo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ResiduosRepository extends JpaRepository<Residuos, Long>{

    @Query("SELECT r FROM Residuos r WHERE r.dataFim IS NULL")
    Page<Residuos> buscaTodosRegistroAtivos(@Param("pageable") Pageable pageable);

    @Query("SELECT r FROM Residuos r WHERE r.id = :id AND r.dataFim IS NULL")
    Optional<Residuos> buscaUmRegistroAtivo(@Param("id") Long id);

    @Query("SELECT r FROM Residuos r " +
            "LEFT JOIN r.pontoColeta p " +
            "LEFT JOIN p.endereco e " +
            "WHERE r.dataFim IS NULL " +
            "AND (:tipoResiduo IS NULL OR r.tipoResiduo = :tipoResiduo) " +
            "AND (:nomeResponsavel IS NULL OR LOWER(r.nomeResponsavel) LIKE LOWER(CONCAT('%', :nomeResponsavel, '%'))) " +
            "AND (:dataInicio IS NULL OR r.dataColeta >= :dataInicio) " +
            "AND (:dataFim IS NULL OR r.dataColeta <= :dataFim) " +
            "AND (:local IS NULL OR LOWER(e.cidade) LIKE LOWER(CONCAT('%', :local, '%'))) " +
            "AND (:pontoColetaId IS NULL OR p.id = :pontoColetaId)")
    Page<Residuos> buscarComFiltros(
            @Param("tipoResiduo") EnumTipoResiduo tipoResiduo,
            @Param("nomeResponsavel") String nomeResponsavel,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim,
            @Param("local") String local,
            @Param("pontoColetaId") Long pontoColetaId,
            Pageable pageable);

    @Query("SELECT SUM(r.peso) FROM Residuos r WHERE r.dataFim IS NULL AND MONTH(r.dataColeta) = :mes AND YEAR(r.dataColeta) = :ano")
    Double somarPesoPorMesEAno(@Param("mes") int mes, @Param("ano") int ano);

    @Query("SELECT r.tipoResiduo as tipo, SUM(r.peso) as peso " +
           "FROM Residuos r " +
           "WHERE r.dataFim IS NULL " +
           "GROUP BY r.tipoResiduo")
    List<Object[]> somarPesoPorTipo();

    @Query("SELECT MONTH(r.dataColeta) as mes, YEAR(r.dataColeta) as ano, SUM(r.peso) as peso " +
           "FROM Residuos r " +
           "WHERE r.dataFim IS NULL " +
           "AND r.dataColeta >= :dataInicio " +
           "GROUP BY YEAR(r.dataColeta), MONTH(r.dataColeta) " +
           "ORDER BY YEAR(r.dataColeta) DESC, MONTH(r.dataColeta) DESC")
    List<Object[]> somarPesoPorMesUltimos(@Param("dataInicio") LocalDate dataInicio);

}
