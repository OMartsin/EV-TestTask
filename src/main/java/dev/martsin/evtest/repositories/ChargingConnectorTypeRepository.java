package dev.martsin.evtest.repositories;

import dev.martsin.evtest.models.ChargingConnectorType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChargingConnectorTypeRepository extends JpaRepository<ChargingConnectorType, Long> {
    Optional<ChargingConnectorType> findByName(String name);
}