package br.com.ecogestor.repository;

import br.com.ecogestor.entidade.Residuos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResiduosRepository extends JpaRepository<Residuos, Long>{
}
