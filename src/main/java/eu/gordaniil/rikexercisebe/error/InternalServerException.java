package eu.gordaniil.rikexercisebe.error;

import eu.gordaniil.rikexercisebe.constant.ErrorMessages;
import lombok.Getter;

@Getter
public class InternalServerException extends GenericException {

    public InternalServerException(String description) {
        super(ErrorMessages.INTERNAL_ERROR, description);
    }

    public InternalServerException(String message, String description) {
        super(message, description);
    }

    public InternalServerException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public InternalServerException(String message, String description, Throwable throwable) {
        super(message, description, throwable);
    }

}
