package henriquef10.api_rastreamento_despesas.application.usecase.expense;

import henriquef10.api_rastreamento_despesas.core.usecases.expense.DeleteExpenseUseCase;
import henriquef10.api_rastreamento_despesas.repository.ExpenseRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteExpenseUseCaseImpl implements DeleteExpenseUseCase {

    private final ExpenseRepository expenseRepository;

    public DeleteExpenseUseCaseImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public void execute(Long id) {
        this.expenseRepository.delete(id);
    }
}
