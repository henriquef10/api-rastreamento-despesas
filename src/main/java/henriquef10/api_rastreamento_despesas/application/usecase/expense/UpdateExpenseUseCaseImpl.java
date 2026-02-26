package henriquef10.api_rastreamento_despesas.application.usecase.expense;

import henriquef10.api_rastreamento_despesas.application.exception.ForbiddenException;
import henriquef10.api_rastreamento_despesas.application.provider.AuthenticatedUserProvider;
import henriquef10.api_rastreamento_despesas.core.entities.category.Category;
import henriquef10.api_rastreamento_despesas.core.entities.expense.Expense;
import henriquef10.api_rastreamento_despesas.core.exception.expense.ExpenseNotFoundException;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.update.UpdateExpenseInput;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.update.UpdateExpenseOutput;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.update.UpdateExpenseUseCase;
import henriquef10.api_rastreamento_despesas.repository.CategoryRepository;
import henriquef10.api_rastreamento_despesas.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UpdateExpenseUseCaseImpl implements UpdateExpenseUseCase {

    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public UpdateExpenseUseCaseImpl(CategoryRepository categoryRepository, ExpenseRepository expenseRepository, AuthenticatedUserProvider authenticatedUserProvider) {
        this.categoryRepository = categoryRepository;
        this.expenseRepository = expenseRepository;
        this.authenticatedUserProvider = authenticatedUserProvider;
    }

    @Override
    public UpdateExpenseOutput execute(UpdateExpenseInput input) {

        Expense expense = this.expenseRepository.findById(input.id()).orElseThrow(() -> new ExpenseNotFoundException("Expense not found"));

        Category category = this.categoryRepository.findById(input.category_id()).orElseThrow(() -> new EntityNotFoundException("Category not found"));

        if(!expense.getUser().getId().equals(authenticatedUserProvider.getAuthenticatedUserId())){
            throw new ForbiddenException("User not authorized to update this expense");
        }

        expense.setName(input.name());
        expense.setDescription(input.description());
        expense.setAmount(input.amount());
        expense.setDueDate(input.dueDate());
        expense.setPaymentDate(input.paymentDate());
        expense.setCategory(category);

        this.expenseRepository.save(expense);

        return new UpdateExpenseOutput(
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
