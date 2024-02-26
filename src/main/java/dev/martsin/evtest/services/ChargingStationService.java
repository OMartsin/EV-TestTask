package dev.martsin.evtest.services;

import dev.martsin.evtest.dto.ChargingStationRequest;
import dev.martsin.evtest.dto.ChargingStationResponse;

import java.util.List;

public interface ChargingStationService {
    List<ChargingStationResponse> findAll();
    ChargingStationResponse findById(Long id);
    ChargingStationResponse save(ChargingStationRequest request);
}
