package br.com.ecogestor.repository;

import br.com.ecogestor.entidade.PontoColeta;
import br.com.ecogestor.enums.EnumTipoResiduo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PontoColetaRepository extends JpaRepository<PontoColeta, Long> {

    @Query("SELECT p FROM PontoColeta p WHERE p.dataFim IS NULL")
    Page<PontoColeta> buscaTodosRegistroAtivos(@Param("pageable") Pageable pageable);

    @Query("SELECT p FROM PontoColeta p WHERE p.id = :id AND p.dataFim IS NULL")
    Optional<PontoColeta> buscaUmRegistroAtivo(@Param("id") Long id);

    @Query("SELECT p FROM PontoColeta p " +
            "LEFT JOIN p.endereco e " +
            "WHERE p.dataFim IS NULL " +
            "AND (:nomePonto IS NULL OR LOWER(p.nomePonto) LIKE LOWER(CONCAT('%', :nomePonto, '%'))) " +
            "AND (:tipoResiduo IS NULL OR p.tipoResiduo = :tipoResiduo) " +
            "AND (:enderecoNome IS NULL OR " +
            "     LOWER(e.logradouro) LIKE LOWER(CONCAT('%', :enderecoNome, '%')) OR " +
            "     LOWER(e.bairro) LIKE LOWER(CONCAT('%', :enderecoNome, '%')) OR " +
            "     LOWER(e.cidade) LIKE LOWER(CONCAT('%', :enderecoNome, '%')) )")
    Page<PontoColeta> buscarComFiltros(
            @Param("nomePonto") String nomePonto,
            @Param("tipoResiduo") String tipoResiduo,
            @Param("enderecoNome") String enderecoNome,
            Pageable pageable);


    @Query(value = "SELECT p FROM PontoColeta p WHERE p.tipoResiduo = :tipoResiduo")
    List<PontoColeta> buscarPorTipoResiduo(EnumTipoResiduo tipoResiduo);

    @Query("SELECT COUNT(p) FROM PontoColeta p WHERE p.dataFim IS NULL")
    long contarTodosRegistrosAtivos();
}
