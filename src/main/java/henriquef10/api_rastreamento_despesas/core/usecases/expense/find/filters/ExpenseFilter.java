package henriquef10.api_rastreamento_despesas.core.usecases.expense.find.filters;

import henriquef10.api_rastreamento_despesas.core.entities.expense.ExpenseStatus;

import java.time.LocalDate;

public record ExpenseFilter(
        String search,
        Long categoryId,
        ExpenseStatus status,
        LocalDate startDate,
        LocalDate endDate
) {
}
