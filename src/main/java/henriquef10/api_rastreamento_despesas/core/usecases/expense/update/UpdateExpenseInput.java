package henriquef10.api_rastreamento_despesas.core.usecases.expense.update;

import henriquef10.api_rastreamento_despesas.core.entities.expense.ExpenseStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateExpenseInput(
        Long id,
        String name,
        String description,
        BigDecimal amount,
        ExpenseStatus status,
        LocalDate dueDate,
        LocalDate paymentDate,
        Long category_id
) {
}
