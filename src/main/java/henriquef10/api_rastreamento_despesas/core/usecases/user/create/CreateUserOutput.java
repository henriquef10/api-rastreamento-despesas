package henriquef10.api_rastreamento_despesas.core.usecases.user.create;

import henriquef10.api_rastreamento_despesas.core.entities.user.UserRole;

public record CreateUserOutput(
        Long id,
        String name,
        String login,
        UserRole role
) {
}
