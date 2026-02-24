package henriquef10.api_rastreamento_despesas.core.usecases.expense.payment;

import henriquef10.api_rastreamento_despesas.core.entities.expense.ExpenseStatus;

import java.time.LocalDate;

public record PaymentExpenseOutput(
        Long expense_id,
        LocalDate payment_date,
        ExpenseStatus status
) {
}
