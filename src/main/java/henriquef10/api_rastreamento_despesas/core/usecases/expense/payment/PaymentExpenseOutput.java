package henriquef10.api_rastreamento_despesas.core.usecases.expense.payment;

import henriquef10.api_rastreamento_despesas.core.entities.expense.ExpenseStatus;

public record PaymentExpenseOutput(
        Long expense_id,
        String message,
        ExpenseStatus status
) {
}
