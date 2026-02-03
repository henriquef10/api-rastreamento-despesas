package henriquef10.api_rastreamento_despesas.core.usecases.user;

public record CreateUserInput(
        String name,
        String login,
        String password
) {
}
