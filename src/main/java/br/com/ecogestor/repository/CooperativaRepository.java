package br.com.ecogestor.repository;

import br.com.ecogestor.entidade.Cooperativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CooperativaRepository extends JpaRepository<Cooperativa, Long> {
}
