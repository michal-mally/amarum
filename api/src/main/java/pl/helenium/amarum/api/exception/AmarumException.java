package pl.helenium.amarum.api.exception;

public class AmarumException extends Exception {

    public AmarumException(String message) {
        super(message);
    }

    public AmarumException(String message, Throwable cause) {
        super(message, cause);
    }

}
