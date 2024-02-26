package dev.martsin.evtest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

/**
 * DTO for {@link dev.martsin.evtest.models.ChargingConnectorType}
 */
@Value
public class ChargingConnectorTypeDto {
    String name;
    public ChargingConnectorTypeDto(@JsonProperty("name") String name) {
        this.name = name;
    }
}