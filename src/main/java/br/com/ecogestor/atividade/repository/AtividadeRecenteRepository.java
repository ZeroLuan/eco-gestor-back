package br.com.ecogestor.atividade.repository;

import br.com.ecogestor.atividade.entity.AtividadeRecente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeRecenteRepository extends JpaRepository<AtividadeRecente, Long> {

    @Query("SELECT a FROM AtividadeRecente a ORDER BY a.data DESC")
    Page<AtividadeRecente> buscarTodasPaginadas(Pageable pageable);
}
