package dev.martsin.evtest.services;

import dev.martsin.evtest.dto.ChargingStationRequest;
import dev.martsin.evtest.dto.ChargingStationResponse;
import dev.martsin.evtest.mappers.ChargingStationMapper;
import dev.martsin.evtest.repositories.ChargingConnectorTypeRepository;
import dev.martsin.evtest.repositories.ChargingStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChargingStationServiceImpl implements ChargingStationService{
    private final ChargingStationRepository chargingStationRepository;
    private final ChargingConnectorTypeRepository chargingConnectorTypeRepository;
    private final ChargingStationMapper chargingStationMapper;

    @Override
    public List<ChargingStationResponse> findAll() {
        return chargingStationRepository.findAll().stream().map(chargingStationMapper::toDto).toList();
    }

    @Override
    public ChargingStationResponse findById(Long id) {
        return chargingStationMapper.toDto(chargingStationRepository.findById(id).orElseThrow());
    }

    @Override
    public ChargingStationResponse save(ChargingStationRequest request) {
        var station = chargingStationMapper.toEntity(request);
        var types = chargingConnectorTypeRepository.findAll();
        for (var connector : station.getConnectors()) {
            connector.setChargingStation(station);
            connector.setType(types.stream()
                    .filter(type -> type.getName().equals(connector.getType().getName()))
                    .findFirst().orElseThrow());
        }
        station = chargingStationRepository.save(station);
        return chargingStationMapper.toDto(station);
    }
}
