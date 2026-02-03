package henriquef10.api_rastreamento_despesas.core.exception.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
