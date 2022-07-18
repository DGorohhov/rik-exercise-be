package eu.gordaniil.rikexercisebe.error;

import eu.gordaniil.rikexercisebe.constant.ErrorMessages;
import lombok.Getter;

@Getter
public class NotFoundException extends GenericException {

    public NotFoundException(String description) {
        super(ErrorMessages.NOT_FOUND, description);
    }

    public NotFoundException(String message, String description) {
        super(message, description);
    }

    public NotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public NotFoundException(String message, String description, Throwable throwable) {
        super(message, description, throwable);
    }

}
