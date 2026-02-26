package henriquef10.api_rastreamento_despesas.application.usecase.expense;

import henriquef10.api_rastreamento_despesas.application.exception.ForbiddenException;
import henriquef10.api_rastreamento_despesas.application.provider.AuthenticatedUserProvider;
import henriquef10.api_rastreamento_despesas.core.entities.expense.Expense;
import henriquef10.api_rastreamento_despesas.core.entities.user.UserRole;
import henriquef10.api_rastreamento_despesas.core.exception.expense.ExpenseNotFoundException;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.find.FindByIdExpenseUseCase;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.find.FindExpenseOutput;
import henriquef10.api_rastreamento_despesas.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class FindByIdExpenseUseCaseImpl implements FindByIdExpenseUseCase {

    private final ExpenseRepository expenseRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public FindByIdExpenseUseCaseImpl(ExpenseRepository expenseRepository, AuthenticatedUserProvider authenticatedUserProvider) {
        this.expenseRepository = expenseRepository;
        this.authenticatedUserProvider = authenticatedUserProvider;
    }

    @Override
    public FindExpenseOutput execute(Long id) {

        Expense expense = this.expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException("Expense not found"));

        if(authenticatedUserProvider.isAuthenticatedUserByRole(UserRole.USER)){
            if(!expense.getUser().getId().equals(authenticatedUserProvider.getAuthenticatedUserId())){
                throw new ForbiddenException("User not authorized to access this expense");
            }
        }

        return new FindExpenseOutput(
                expense.getId(),
                expense.getName(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getStatus(),
                expense.getDueDate(),
                expense.getPaymentDate(),
                expense.getCategory().getName()
        );

    }

}
