package henriquef10.api_rastreamento_despesas.controller.dto;

public record ApiResponse<T>(
        String message,
        T data
) {
}
