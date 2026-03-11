package henriquef10.api_rastreamento_despesas.application.usecase.expense;

import henriquef10.api_rastreamento_despesas.application.exception.ForbiddenException;
import henriquef10.api_rastreamento_despesas.application.provider.AuthenticatedUserProvider;
import henriquef10.api_rastreamento_despesas.core.entities.expense.Expense;
import henriquef10.api_rastreamento_despesas.core.exception.expense.ExpenseNotFoundException;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.payment.PaymentExpenseOutput;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.payment.PaymentRemoveExpenseOutput;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.payment.PaymentRemoveExpenseUseCase;
import henriquef10.api_rastreamento_despesas.repository.ExpenseRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PaymentRemoveExpenseUseCaseImpl implements PaymentRemoveExpenseUseCase {

    private final ExpenseRepository expenseRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public PaymentRemoveExpenseUseCaseImpl(ExpenseRepository expenseRepository, AuthenticatedUserProvider authenticatedUserProvider) {
        this.expenseRepository = expenseRepository;
        this.authenticatedUserProvider = authenticatedUserProvider;
    }

    @Override
    public PaymentRemoveExpenseOutput execute(Long id) {

        Expense expense = this.expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException("Expense not found"));

        if(!expense.getUser().getId().equals(authenticatedUserProvider.getAuthenticatedUserId())){
            throw new ForbiddenException("User not authorized to remove pay this expense");
        }

        expense.removePayment();

        this.expenseRepository.save(expense);

        return new PaymentRemoveExpenseOutput(expense.getId(), expense.getStatus());

    }

}
