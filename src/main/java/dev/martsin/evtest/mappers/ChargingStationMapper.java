package dev.martsin.evtest.mappers;

import dev.martsin.evtest.dto.ChargingConnectorRequest;
import dev.martsin.evtest.dto.ChargingStationResponse;
import dev.martsin.evtest.models.ChargingConnector;
import dev.martsin.evtest.models.ChargingConnectorType;
import dev.martsin.evtest.models.ChargingStation;
import dev.martsin.evtest.dto.ChargingStationRequest;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ChargingStationMapper {
    ChargingStationResponse toDto(ChargingStation chargingStation);
    //map connectors[i].typeName to connectors[i].type.name
    @Mapping(target = "connectors", source = "connectors", qualifiedByName = "mapConnectorTypes")
    ChargingStation toEntity(ChargingStationRequest chargingStationRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ChargingStation partialUpdate(ChargingStationRequest chargingStationRequest,
                                  @MappingTarget ChargingStation chargingStation);

    @Named("mapConnectorTypes")
    default ChargingConnector mapConnectorTypes(ChargingConnectorRequest chargingConnectorRequest) {
        return new ChargingConnector(new ChargingConnectorType(chargingConnectorRequest.getTypeName()),
                chargingConnectorRequest.getMaxKWPower());
    }
}