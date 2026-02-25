package henriquef10.api_rastreamento_despesas.controller.dto;

import henriquef10.api_rastreamento_despesas.core.entities.user.UserRole;

public record CreateUserRequest(
        String name,
        String login,
        String password,
        UserRole role
) {
}
