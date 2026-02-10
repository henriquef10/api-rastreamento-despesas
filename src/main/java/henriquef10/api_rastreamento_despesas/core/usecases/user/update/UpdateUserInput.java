package henriquef10.api_rastreamento_despesas.core.usecases.user.update;

public record UpdateUserInput(
        Long id,
        String name,
        String login
) {
}
