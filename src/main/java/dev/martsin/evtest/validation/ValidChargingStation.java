package dev.martsin.evtest.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ChargingStationValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidChargingStation {
    String message() default "Invalid Charging Station";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
