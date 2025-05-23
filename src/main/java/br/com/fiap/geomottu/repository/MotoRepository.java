package br.com.fiap.geomottu.repository;

import br.com.fiap.geomottu.model.entities.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MotoRepository extends JpaRepository<Moto, Long> {

    Optional<Moto> findByPlacaIgnoreCase(String placa);
    Optional<Moto> findByChassiIgnoreCase(String chassi);
}
