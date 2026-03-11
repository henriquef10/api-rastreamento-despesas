package henriquef10.api_rastreamento_despesas.core.usecases.expense.payment;

import henriquef10.api_rastreamento_despesas.core.entities.expense.ExpenseStatus;

import java.time.LocalDate;

public record PaymentRemoveExpenseOutput(
        Long expense_id,
        ExpenseStatus status
) {
}
