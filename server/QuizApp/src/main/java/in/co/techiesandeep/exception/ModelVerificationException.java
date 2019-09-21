package in.co.techiesandeep.exception;

public class ModelVerificationException   extends RuntimeException {

    public ModelVerificationException() {
        super();
    }

    public ModelVerificationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModelVerificationException(String message) {
        super(message);
    }

    public ModelVerificationException(Throwable cause) {
        super(cause);
    }
}