package henriquef10.api_rastreamento_despesas.controller.dto;

import java.time.LocalDateTime;

public record ExceptionResponse(
        int status,
        String message,
        LocalDateTime timestamp
) {
}
