package eu.gordaniil.rikexercisebe.error;

import eu.gordaniil.rikexercisebe.constant.ErrorMessages;
import lombok.Getter;

@Getter
public class BadInputException extends GenericException {

    public BadInputException(String description) {
        super(ErrorMessages.BAD_INPUT, description);
    }

    public BadInputException(String message, String description) {
        super(message, description);
    }

    public BadInputException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public BadInputException(String message, String description, Throwable throwable) {
        super(message, description, throwable);
    }

}
