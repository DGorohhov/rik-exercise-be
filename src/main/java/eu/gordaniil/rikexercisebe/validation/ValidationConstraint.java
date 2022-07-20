package eu.gordaniil.rikexercisebe.validation;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationConstraint {

    public final String UUID_V4_REGEXP = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$";
    public final String PERSONAL_IDENTIFICATION_CODE_REGEXP = "^[0-9]{11}$";

}
