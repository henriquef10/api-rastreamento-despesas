package henriquef10.api_rastreamento_despesas.core.usecases.expense.create;

import henriquef10.api_rastreamento_despesas.core.entities.category.Category;
import henriquef10.api_rastreamento_despesas.core.entities.expense.ExpenseStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateExpenseOutput(
        Long id,
        String name,
        String description,
        BigDecimal amount,
        ExpenseStatus status,
        LocalDate dueDate,
        LocalDate paymentDate,
        Category category
) {
}
