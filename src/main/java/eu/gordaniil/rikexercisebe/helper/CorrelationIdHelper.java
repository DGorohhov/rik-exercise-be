package eu.gordaniil.rikexercisebe.helper;

import org.slf4j.MDC;

import java.util.Optional;

public class CorrelationIdHelper {

    private CorrelationIdHelper() {}

    public static String correlationId() {
        return Optional
                .ofNullable(MDC.get("correlationId"))
                .orElse("-");
    }
}
