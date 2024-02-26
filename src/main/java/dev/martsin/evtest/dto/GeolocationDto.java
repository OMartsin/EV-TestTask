package dev.martsin.evtest.dto;

import dev.martsin.evtest.models.Geolocation;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Geolocation}
 */
@Value
public class GeolocationDto implements Serializable {
    @DecimalMin(value = "-90.00", message = "Latitude should not be less than -90")
    @DecimalMax(value = "90.00", message = "Latitude should not be greater than 90")
    Double latitude;

    @DecimalMin(value = "-180.00", message = "Longitude should not be less than -180")
    @DecimalMax(value = "180.00", message = "Longitude should not be greater than 180")
    Double longitude;
}