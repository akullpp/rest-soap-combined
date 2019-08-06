package de.akull.geoloc.exception.handler;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

import de.akull.geoloc.exception.GeolocationException;
import java.util.UUID;
import javax.annotation.Priority;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Priority(HIGHEST_PRECEDENCE)
public class GeolocationExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());

    @ResponseBody
    @ExceptionHandler(GeolocationException.class)
    public ExceptionResponse handleGeolocationException(GeolocationException exception, HttpServletResponse response) {
        UUID uuid = UUID.randomUUID();

        if (exception.hasThrowable()) {
            log.error("{} - {} - {}", uuid, exception.getKey(), exception.getThrowable());
        } else {
            log.error("{} - {} - {}", uuid, exception.getKey(), exception.getMessage());
        }
        response.setStatus(exception.getStatus());
        return new ExceptionResponse(uuid, exception.getKey(), exception.getMessage());
    }
}