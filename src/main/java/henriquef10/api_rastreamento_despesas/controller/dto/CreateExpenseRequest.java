package henriquef10.api_rastreamento_despesas.controller.dto;

import henriquef10.api_rastreamento_despesas.core.entities.expense.ExpenseStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateExpenseRequest(
        String name,
        String description,
        BigDecimal amount,
        LocalDate dueDate,
        LocalDate paymentDate,
        Long category_id
) {
}
