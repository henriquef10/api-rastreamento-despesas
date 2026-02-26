package henriquef10.api_rastreamento_despesas.application.usecase.expense;

import henriquef10.api_rastreamento_despesas.application.exception.ForbiddenException;
import henriquef10.api_rastreamento_despesas.application.provider.AuthenticatedUserProvider;
import henriquef10.api_rastreamento_despesas.core.entities.expense.Expense;
import henriquef10.api_rastreamento_despesas.core.exception.expense.ExpenseNotFoundException;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.payment.PaymentExpenseOutput;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.payment.PaymentExpenseUseCase;
import henriquef10.api_rastreamento_despesas.repository.ExpenseRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PaymentExpenseUseCaseImpl implements PaymentExpenseUseCase {

    private final ExpenseRepository expenseRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public PaymentExpenseUseCaseImpl(ExpenseRepository expenseRepository, AuthenticatedUserProvider authenticatedUserProvider) {
        this.expenseRepository = expenseRepository;
        this.authenticatedUserProvider = authenticatedUserProvider;
    }

    @Override
    public PaymentExpenseOutput execute(Long id, LocalDate paymentDate) {

        Expense expense = this.expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException("Expense not found"));

        if(!expense.getUser().getId().equals(authenticatedUserProvider.getAuthenticatedUserId())){
            throw new ForbiddenException("User not authorized to pay this expense");
        }

        expense.pay(paymentDate);

        this.expenseRepository.save(expense);

        return new PaymentExpenseOutput(expense.getId(), expense.getPaymentDate(), expense.getStatus());

    }

}
