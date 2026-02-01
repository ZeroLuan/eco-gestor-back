package br.com.ecogestor.cooperativa.repository;
  
import br.com.ecogestor.cooperativa.entity.Cooperativa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CooperativaRepository extends JpaRepository<Cooperativa, Long> {

    @Query("SELECT c FROM Cooperativa c WHERE c.dataFim IS NULL")
    List<Cooperativa> buscaTodosRegistroAtivos();

    @Query("SELECT c FROM Cooperativa c WHERE c.dataFim IS NULL")
    Page<Cooperativa> buscaTodosRegistroAtivos(Pageable pageable);

    @Query("SELECT c FROM Cooperativa c WHERE c.id = :id AND c.dataFim IS NULL")
    Optional<Cooperativa> buscaUmRegistroAtivo(@Param("id") Long id);

    @Query("SELECT c FROM Cooperativa c " +
            "LEFT JOIN c.endereco e " +
            "WHERE c.dataFim IS NULL " +
            "AND (:nomeEmpresa IS NULL OR LOWER(c.nomeEmpresa) LIKE LOWER(CONCAT('%', :nomeEmpresa, '%'))) " +
            "AND (:cnpj IS NULL OR c.cnpj = :cnpj) " +
            "AND (:telefone IS NULL OR c.telefone = :telefone) " +
            "AND (:statusCooperativa IS NULL OR c.statusCooperativa = :statusCooperativa) " +
            "AND (:enderecoNome IS NULL OR " +
            "     LOWER(e.logradouro) LIKE LOWER(CONCAT('%', :enderecoNome, '%')) OR " +
            "     LOWER(e.bairro) LIKE LOWER(CONCAT('%', :enderecoNome, '%')) OR " +
            "     LOWER(e.cidade) LIKE LOWER(CONCAT('%', :enderecoNome, '%')) )")
    Page<Cooperativa> buscarComFiltros(
            @Param("nomeEmpresa") String nomeEmpresa,
            @Param("cnpj") String cnpj,
            @Param("telefone") String telefone,
            @Param("statusCooperativa") Boolean statusCooperativa,
            @Param("enderecoNome") String enderecoNome,
            Pageable pageable);

    @Query("SELECT COUNT(c) FROM Cooperativa c WHERE c.dataFim IS NULL")
    Long buscarTotalCooperativasAtivas();
}
