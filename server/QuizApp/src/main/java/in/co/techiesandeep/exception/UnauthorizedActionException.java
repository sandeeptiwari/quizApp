package in.co.techiesandeep.exception;

public class UnauthorizedActionException extends RuntimeException {

    public UnauthorizedActionException() {
        super();
    }

    public UnauthorizedActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedActionException(String message) {
        super(message);
    }

    public UnauthorizedActionException(Throwable cause) {
        super(cause);
    }
}