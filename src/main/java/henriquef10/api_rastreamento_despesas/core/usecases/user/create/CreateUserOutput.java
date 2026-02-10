package henriquef10.api_rastreamento_despesas.core.usecases.user.create;

public record CreateUserOutput(
        Long id,
        String name,
        String login
) {
}
