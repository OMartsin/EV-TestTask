package dev.martsin.evtest.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link dev.martsin.evtest.models.ChargingStation}
 */
@Value
public class ChargingStationResponse implements Serializable {
    Long id;
    String title;
    String description;
    String address;
    GeolocationDto location;
    Boolean isPublic;
    Set<ChargingConnectorResponse> connectors;

    /**
     * DTO for {@link dev.martsin.evtest.models.ChargingConnector}
     */
    @Value
    public static class ChargingConnectorResponse implements Serializable {
        Long id;
        ChargingConnectorTypeDto type;
        Double maxKWPower;
    }
}