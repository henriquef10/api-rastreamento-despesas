package henriquef10.api_rastreamento_despesas.application.exception;

public class UnauthenticatedException extends RuntimeException {
    public UnauthenticatedException(String message) {
        super(message);
    }
}
