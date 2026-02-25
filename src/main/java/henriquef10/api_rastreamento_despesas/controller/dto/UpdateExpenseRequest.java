package henriquef10.api_rastreamento_despesas.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateExpenseRequest(
        String name,
        String description,
        BigDecimal amount,
        LocalDate dueDate,
        LocalDate paymentDate,
        Long category_id
) {
}
