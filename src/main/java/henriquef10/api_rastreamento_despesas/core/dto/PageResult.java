package henriquef10.api_rastreamento_despesas.core.dto;

import java.util.List;

public record PageResult<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        String sortBy,
        String direction
) { }
