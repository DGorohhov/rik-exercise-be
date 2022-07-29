package eu.gordaniil.rikexercisebe.aop;

import eu.gordaniil.rikexercisebe.error.*;
import eu.gordaniil.rikexercisebe.helper.CorrelationIdHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class DefaultRestExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public RestExceptionResponse handleException(Exception e) {
        log.error("Handling generic exception: ({})", e.getMessage());

        var response = new RestExceptionResponse(
            "Something went wrong", "Please contact our support", CorrelationIdHelper.correlationId()
        );

        log.debug("Sent client an exception response: ({}) ({})", response.getError(), response.getExceptionData());

        return response;
    }

    @ExceptionHandler(GenericException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public RestExceptionResponse handleException(GenericException e) {
        log.error("Handling REST exception: ({}) ({})", e.getMessage(), e.getDescription());

        var response = new RestExceptionResponse(e.getMessage(), e.getDescription(), CorrelationIdHelper.correlationId());

        log.debug("Sent client an exception response: ({}) ({})", response.getError(), response.getExceptionData());

        return response;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public RestExceptionResponse handleNotFoundException(GenericException e) {
        log.error("Handling not found exception: ({}) ({})", e.getMessage(), e.getDescription());

        var response = new RestExceptionResponse(e.getMessage(), e.getDescription(), CorrelationIdHelper.correlationId());

        log.debug("Sent client a not found exception response: ({}) ({})", response.getError(), response.getExceptionData());

        return response;
    }

    @ExceptionHandler(BadInputException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public RestExceptionResponse handleBadRequestException(BadInputException e) {
        log.error("Handling bad input exception: ({}) ({})", e.getMessage(), e.getDescription());

        var response = new RestExceptionResponse(e.getMessage(), e.getDescription(), CorrelationIdHelper.correlationId());

        log.debug("Sent client a bad input exception response: ({}) ({})", response.getError(), response.getExceptionData());

        return response;
    }

    @ExceptionHandler(InternalServerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public RestExceptionResponse handleInternalServerException(InternalServerException e) {
        log.error("Handling internal server exception: ({}) ({})", e.getMessage(), e.getDescription());

        var response = new RestExceptionResponse(e.getMessage(), e.getDescription(), CorrelationIdHelper.correlationId());

        log.debug("Sent client internal server exception response: ({}) ({})", response.getError(), response.getExceptionData());

        return response;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public RestExceptionResponse handleConstraintViolationException(Exception e) {
        log.error("Handling constraint violation exception: ({})", e.getMessage());

        var response = new RestExceptionResponse(
            "Constraint Violation exception", "Please contact our support", CorrelationIdHelper.correlationId()
        );

        log.debug("Sent client a constraint violation exception response: ({}) ({})", response.getError(), response.getExceptionData());

        return response;
    }

    @ExceptionHandler({
        org.hibernate.exception.ConstraintViolationException.class,
        DataIntegrityViolationException.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public RestExceptionResponse handleHibernateConstraintViolationException(Exception e) {
        log.error("Handling constraint / data integrity violation exception: ({})", e.getMessage());

        var response = new RestExceptionResponse(
            "Constraint / Data integrity Violation exception", description(e), CorrelationIdHelper.correlationId()
        );

        log.debug("Sent client a constraint / data integrity violation exception response:({}) ({})", response.getError(), response.getExceptionData());

        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public RestExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("Handling method argument not valid exception: ({})", e.getMessage());

        var response = new RestExceptionResponse("Argument invalid exception", invalidParams(e), CorrelationIdHelper.correlationId());

        log.debug("Sent client a method argument not valid exception response: ({}) ({})", response.getError(), response.getExceptionData());

        return response;
    }

    private String invalidParams(MethodArgumentNotValidException e) {
        var exceptionDetailsNull = Objects.isNull(e) || Objects.isNull(e.getBindingResult().getFieldError());
        if (exceptionDetailsNull || e.getBindingResult().getFieldErrors().isEmpty()) {
            log.error("Exception details missing");
            return "Exception details missing. Please contact our support";
        }

        var messageFormat = "invalid value '%s' submitted for field '%s'. Message: %s, ";
        var invalidParams = new StringBuilder();
        e.getBindingResult().getFieldErrors()
            .forEach(p -> invalidParams
                .append(String.format(
                    messageFormat,
                    p.getRejectedValue(),
                    p.getField(),
                    p.getDefaultMessage()
                )));

        return invalidParams.toString();
    }

    private String description(Exception e) {
        log.debug("Parsing exception description ({})", e.getMessage());
        if (Objects.nonNull(e.getCause()) && Objects.nonNull(e.getCause().getCause())) {
            var cause = e.getCause().getCause();
            log.debug("Parsing inner exception cause ({})", cause.toString());

            return cause.toString();
        }

        if (Objects.nonNull(e.getCause())) {
            var cause = e.getCause();
            log.debug("Parsing exception cause: ({})", cause.toString());

            return cause.toString();
        }

        log.warn("Exception cause absent, adding generic missing error description");

        return "Exception description missing";
    }
}

