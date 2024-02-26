package dev.martsin.evtest.repositories;

import dev.martsin.evtest.models.ChargingStation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChargingStationRepository extends JpaRepository<ChargingStation, Long> {
    @EntityGraph(attributePaths = {"location", "connectors"})
    List<ChargingStation> findAll();

    @EntityGraph(attributePaths = {"location", "connectors"})
    Optional<ChargingStation> findById(Long id);
}
