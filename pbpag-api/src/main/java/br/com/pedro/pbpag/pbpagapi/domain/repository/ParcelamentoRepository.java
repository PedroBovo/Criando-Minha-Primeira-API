package br.com.pedro.pbpag.pbpagapi.domain.repository;

import br.com.pedro.pbpag.pbpagapi.domain.model.Parcelamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelamentoRepository extends JpaRepository<Parcelamento, Long> {
}
