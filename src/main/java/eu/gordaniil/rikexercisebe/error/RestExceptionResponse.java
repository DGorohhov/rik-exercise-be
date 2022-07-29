package eu.gordaniil.rikexercisebe.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestExceptionResponse {

    private String error;
    private String exceptionData;
    private String correlationId;

}
