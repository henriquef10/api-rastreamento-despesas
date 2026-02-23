package henriquef10.api_rastreamento_despesas.application.usecase.expense;

import henriquef10.api_rastreamento_despesas.core.entities.category.Category;
import henriquef10.api_rastreamento_despesas.core.entities.expense.Expense;
import henriquef10.api_rastreamento_despesas.core.entities.expense.ExpenseStatus;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.create.CreateExpenseInput;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.create.CreateExpenseUseCase;
import henriquef10.api_rastreamento_despesas.repository.CategoryRepository;
import henriquef10.api_rastreamento_despesas.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;

public class CreateExpenseService implements CreateExpenseUseCase {

    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;

    public CreateExpenseService(CategoryRepository categoryRepository, ExpenseRepository expenseRepository) {
        this.categoryRepository = categoryRepository;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public void execute(CreateExpenseInput input) {

        Expense expense = new Expense();
        expense.setName(input.name());
        expense.setDescription(input.description());
        expense.setAmount(input.amount());
        expense.setStatus(ExpenseStatus.PENDING);
        expense.setDueDate(input.dueDate());
        expense.setPaymentDate(input.paymentDate());

        Category category = this.categoryRepository.findById(input.category_id()).orElseThrow(() -> new EntityNotFoundException("Category not found"));

        expense.setCategory(category);

        this.expenseRepository.save(expense);

    }
}
