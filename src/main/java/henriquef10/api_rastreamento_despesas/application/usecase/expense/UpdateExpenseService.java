package henriquef10.api_rastreamento_despesas.application.usecase.expense;

import henriquef10.api_rastreamento_despesas.core.entities.category.Category;
import henriquef10.api_rastreamento_despesas.core.entities.expense.Expense;
import henriquef10.api_rastreamento_despesas.core.entities.expense.ExpenseStatus;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.create.CreateExpenseInput;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.create.CreateExpenseUseCase;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.update.UpdateExpenseInput;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.update.UpdateExpenseUseCase;
import henriquef10.api_rastreamento_despesas.repository.CategoryRepository;
import henriquef10.api_rastreamento_despesas.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;

public class UpdateExpenseService implements UpdateExpenseUseCase {

    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;

    public UpdateExpenseService(CategoryRepository categoryRepository, ExpenseRepository expenseRepository) {
        this.categoryRepository = categoryRepository;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public void execute(UpdateExpenseInput input) {



        Expense expense = this.expenseRepository.findById(input.id()).orElseThrow(() -> new EntityNotFoundException("Expense not found"));

        Category category = this.categoryRepository.findById(input.category_id()).orElseThrow(() -> new EntityNotFoundException("Category not found"));

        expense.setName(input.name());
        expense.setDescription(input.description());
        expense.setAmount(input.amount());
        expense.setDueDate(input.dueDate());
        expense.setPaymentDate(input.paymentDate());
        expense.setCategory(category);

        this.expenseRepository.save(expense);

    }
}
