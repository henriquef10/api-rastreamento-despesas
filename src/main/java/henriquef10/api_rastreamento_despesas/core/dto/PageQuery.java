package henriquef10.api_rastreamento_despesas.core.dto;

public record PageQuery(
        int page,
        int size,
        String sortBy,
        String direction
) { }
