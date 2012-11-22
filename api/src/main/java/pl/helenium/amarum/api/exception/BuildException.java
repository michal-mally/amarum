package pl.helenium.amarum.api.exception;

public class BuildException extends AmarumException {

    public BuildException(String message) {
        super(message);
    }

    public BuildException(String message, Throwable cause) {
        super(message, cause);
    }

}
