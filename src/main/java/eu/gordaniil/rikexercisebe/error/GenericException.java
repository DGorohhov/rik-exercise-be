package eu.gordaniil.rikexercisebe.error;

import lombok.Getter;

@Getter
public class GenericException extends RuntimeException {

    private String message;
    private String description;

    public GenericException(String message) {
        super(message);
        this.message = message;
    }

    public GenericException(String message, String description) {
        super(message);
        this.message = message;
        this.description = description;
    }

    public GenericException(String message, Throwable throwable) {
        super(throwable);
        this.message = message;
    }

    public GenericException(String message, String description, Throwable throwable) {
        super(throwable);
        this.message = message;
        this.description = description;
    }
}
