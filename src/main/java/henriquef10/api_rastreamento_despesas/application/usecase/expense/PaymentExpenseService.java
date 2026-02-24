package henriquef10.api_rastreamento_despesas.application.usecase.expense;

import henriquef10.api_rastreamento_despesas.core.entities.expense.Expense;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.payment.PaymentExpenseOutput;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.payment.PaymentExpenseUseCase;
import henriquef10.api_rastreamento_despesas.repository.ExpenseRepository;

import java.time.LocalDate;

public class PaymentExpenseService implements PaymentExpenseUseCase {

    private final ExpenseRepository expenseRepository;

    public PaymentExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public PaymentExpenseOutput execute(Long id, LocalDate paymentDate) {

        Expense expense = this.expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));

        expense.pay(paymentDate);

        this.expenseRepository.save(expense);

        return new PaymentExpenseOutput(expense.getId(), expense.getPaymentDate(), expense.getStatus());

    }

}
