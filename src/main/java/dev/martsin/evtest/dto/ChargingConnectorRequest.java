package dev.martsin.evtest.dto;

import dev.martsin.evtest.models.ChargingConnector;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ChargingConnector}
 */
@Value
public class ChargingConnectorRequest implements Serializable {
    @NotBlank
    String typeName;
    @NotNull
    Double maxKWPower;
}