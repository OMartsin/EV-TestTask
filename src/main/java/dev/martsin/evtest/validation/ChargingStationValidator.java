package dev.martsin.evtest.validation;

import dev.martsin.evtest.dto.ChargingConnectorRequest;
import dev.martsin.evtest.dto.ChargingStationRequest;
import dev.martsin.evtest.models.ChargingConnectorType;
import dev.martsin.evtest.repositories.ChargingConnectorTypeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ChargingStationValidator implements ConstraintValidator<ValidChargingStation, ChargingStationRequest> {
    private final ChargingConnectorTypeRepository chargingConnectorTypeRepository;
    @Override
    public boolean isValid(ChargingStationRequest station, ConstraintValidatorContext context) {
        if(station.getLocation() != null && !isGeolocationValid(station)){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Geolocation coordinates must be valid " +
                            "Latitude must be from -90 to 90, longitude must be from -180 to 180.")
                    .addConstraintViolation();
            return false;
        }
        List<String> existingTypes = chargingConnectorTypeRepository.findAll().stream()
                .map(ChargingConnectorType::getName).collect(Collectors.toList());
        if (!station.getIsPublic()) {
            return isConnectorsValid(station.getConnectors(), existingTypes, context);
        } else {
            return isConnectorsValid(station.getConnectors(), existingTypes, context)
                    && isStationPublicPropertiesValid(station, context);
        }
    }

    private boolean isStationPublicPropertiesValid(ChargingStationRequest station, ConstraintValidatorContext context) {
        var res = station.getTitle() != null && !station.getTitle().trim().isEmpty()
                && station.getDescription() != null && !station.getDescription().trim().isEmpty()
                && station.getAddress() != null && !station.getAddress().trim().isEmpty()
                && station.getLocation() != null;
        if (station.getTitle() != null && station.getTitle().length() > 32) {
            addConstraintViolation(context, "The title must not exceed 32 characters.");
            return false;
        }
        if (station.getDescription() != null && station.getDescription().length() > 128) {
            addConstraintViolation(context, "The description must not exceed 128 characters.");
            return false;
        }
        if (station.getAddress() != null && station.getAddress().length() > 128) {
            addConstraintViolation(context, "The address must not exceed 128 characters.");
            return false;
        }
        if (!res) {
            addConstraintViolation(context, "The title, description, address and location must not be blank.");
        }
        return res;
    }

    private boolean isGeolocationValid(ChargingStationRequest station) {
        return station.getLocation().getLatitude() != null
                && station.getLocation().getLongitude() != null && station.getLocation().getLatitude() >= -90
                && station.getLocation().getLatitude() <= 90 && station.getLocation().getLongitude() >= -180
                && station.getLocation().getLongitude() <= 180;
    }

    private boolean isConnectorsValid(List<ChargingConnectorRequest> connectors, List<String> existingTypes,
                                      ConstraintValidatorContext context) {
        if(connectors != null ){
            if(!isConnectorsMaxPowerValid(connectors))
            {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Max power of connectors must be greater than 0")
                        .addConstraintViolation();
                return false;
            }
            if(connectors.size() < 1 || connectors.size() > 8){
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("The num of 'connectors' must be from 1 to 8")
                        .addConstraintViolation();
                return false;
            }
            if(!isConnectorsPortTypeValid(connectors, existingTypes)){
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Connectors must be of existing types: " +
                                String.join(", ", existingTypes))
                        .addConstraintViolation();
                return false;
            }
        }
        return true;
    }

    private boolean isConnectorsPortTypeValid(List<ChargingConnectorRequest> connectors, List<String> existingTypes) {
        for (ChargingConnectorRequest connector : connectors) {
            if (!existingTypes.contains(connector.getTypeName())) {
                return false;
            }
        }
        return true;
    }

    private boolean isConnectorsMaxPowerValid(List<ChargingConnectorRequest> connectors) {
        return connectors.stream().allMatch(connector -> connector.getMaxKWPower() != null
                && connector.getMaxKWPower() > 0);
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}

