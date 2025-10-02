package shared.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        logger.error("Unhandled exception occurred", ex);
        logger.debug("Exception details - Type: {}, Message: {}", ex.getClass().getName(), ex.getMessage());

        // Return a generic error response
        return new ResponseEntity<>(
                "An unexpected error occurred. Please try again later.",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        // Log the exception (optional)
        logger.error("Resource not found exception occurred", ex);
        logger.debug("Resource not found details - Type: {}, Message: {}", ex.getClass().getName(), ex.getMessage());

        // Return a 404 Not Found response
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
        // Log the exception (optional)
        logger.error("Bad request exception occurred", ex);
        logger.debug("Bad request details - Type: {}, Message: {}", ex.getClass().getName(), ex.getMessage());

        // Return a 400 Bad Request response
        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        // Log the exception (optional)
        logger.error("Illegal argument exception occurred", ex);
        logger.debug("Illegal argument details - Type: {}, Message: {}", ex.getClass().getName(), ex.getMessage());

        // Return a 400 Bad Request response
        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

}
