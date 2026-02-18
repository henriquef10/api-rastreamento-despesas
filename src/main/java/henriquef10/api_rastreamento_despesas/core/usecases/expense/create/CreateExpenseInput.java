package henriquef10.api_rastreamento_despesas.core.usecases.expense.create;

import henriquef10.api_rastreamento_despesas.core.entities.expense.ExpenseStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateExpenseInput(
        Long user_id,
        String name,
        String description,
        BigDecimal amount,
        ExpenseStatus status,
        LocalDate dueDate,
        LocalDate paymentDate,
        Long category_id
) {
}
