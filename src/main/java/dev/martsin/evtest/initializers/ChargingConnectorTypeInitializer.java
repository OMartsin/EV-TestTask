package dev.martsin.evtest.initializers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import dev.martsin.evtest.repositories.ChargingConnectorTypeRepository;
import dev.martsin.evtest.models.ChargingConnectorType;
import java.util.Arrays;
import java.util.List;

@Component
public class ChargingConnectorTypeInitializer implements CommandLineRunner {

    private final ChargingConnectorTypeRepository typeRepository;

    public ChargingConnectorTypeInitializer(ChargingConnectorTypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> defaultTypes = Arrays.asList("Type 1", "CCS", "CHAdeMO", "Type 2", "Tesla Supercharger");

        defaultTypes.forEach(typeName -> {
            if (typeRepository.findByName(typeName).isEmpty()) {
                ChargingConnectorType newType = new ChargingConnectorType(typeName);
                typeRepository.save(newType);
            }
        });
    }
}
