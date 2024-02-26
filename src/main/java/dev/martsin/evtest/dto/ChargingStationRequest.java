package dev.martsin.evtest.dto;

import dev.martsin.evtest.models.ChargingStation;
import dev.martsin.evtest.validation.ValidChargingStation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link ChargingStation}
 */
@Value
@ValidChargingStation
public class ChargingStationRequest implements Serializable {
    String title;
    String description;
    String address;
    GeolocationDto location;
    Boolean isPublic;
    @NotNull(message = "The field 'connectors' must not be null")
    @Size(message = "The num of 'connectors' must be from 1 to 8", min = 1, max = 8)
    List<ChargingConnectorRequest> connectors;
}