package henriquef10.api_rastreamento_despesas.application.usecase.expense;

import henriquef10.api_rastreamento_despesas.application.provider.AuthenticatedUserProvider;
import henriquef10.api_rastreamento_despesas.core.entities.category.Category;
import henriquef10.api_rastreamento_despesas.core.entities.expense.Expense;
import henriquef10.api_rastreamento_despesas.core.entities.expense.ExpenseStatus;
import henriquef10.api_rastreamento_despesas.core.entities.user.User;
import henriquef10.api_rastreamento_despesas.core.exception.user.UserNotFoundException;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.create.CreateExpenseInput;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.create.CreateExpenseOutput;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.create.CreateExpenseUseCase;
import henriquef10.api_rastreamento_despesas.repository.CategoryRepository;
import henriquef10.api_rastreamento_despesas.repository.ExpenseRepository;
import henriquef10.api_rastreamento_despesas.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CreateExpenseUseCaseImpl implements CreateExpenseUseCase {

    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;
    private final UserRepository userRepository;

    public CreateExpenseUseCaseImpl(
            CategoryRepository categoryRepository, ExpenseRepository expenseRepository,
            AuthenticatedUserProvider authenticatedUserProvider, UserRepository userRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.expenseRepository = expenseRepository;
        this.authenticatedUserProvider = authenticatedUserProvider;
        this.userRepository = userRepository;
    }

    @Override
    public CreateExpenseOutput execute(CreateExpenseInput input) {

        Long userId =  authenticatedUserProvider.getAuthenticatedUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Expense expense = new Expense();
        expense.setName(input.name());
        expense.setDescription(input.description());
        expense.setAmount(input.amount());
        expense.setDueDate(input.dueDate());
        expense.setPaymentDate(input.paymentDate());
        expense.setUser(user);

        if(input.paymentDate() != null){
            expense.setStatus(ExpenseStatus.PAID);
        }else if (input.dueDate() != null && input.dueDate().isBefore(LocalDate.now())){
            expense.setStatus(ExpenseStatus.PENDING);
        }else if(input.dueDate() != null && input.dueDate().isAfter(LocalDate.now())){
            expense.setStatus(ExpenseStatus.DUE);
        }else{
            expense.setStatus(ExpenseStatus.PENDING);
        }

        Category category = this.categoryRepository.findById(input.category_id()).orElseThrow(() -> new EntityNotFoundException("Category not found"));

        expense.setCategory(category);

        this.expenseRepository.save(expense);

        return new CreateExpenseOutput(
                expense.getId(),
                expense.getName(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getStatus(),
                expense.getDueDate(),
                expense.getPaymentDate(),
                expense.getCategory()
        );
    }
}
