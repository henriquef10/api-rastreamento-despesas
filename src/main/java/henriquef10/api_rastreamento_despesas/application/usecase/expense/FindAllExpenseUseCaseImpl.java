package henriquef10.api_rastreamento_despesas.application.usecase.expense;

import henriquef10.api_rastreamento_despesas.application.provider.AuthenticatedUserProvider;
import henriquef10.api_rastreamento_despesas.core.entities.user.UserRole;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.find.FindAllExpenseUseCase;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.find.FindExpenseOutput;
import henriquef10.api_rastreamento_despesas.repository.CategoryRepository;
import henriquef10.api_rastreamento_despesas.repository.ExpenseRepository;
import henriquef10.api_rastreamento_despesas.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllExpenseUseCaseImpl implements FindAllExpenseUseCase {

    private final ExpenseRepository expenseRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;


    public FindAllExpenseUseCaseImpl(
            ExpenseRepository expenseRepository,
            AuthenticatedUserProvider authenticatedUserProvider
    ) {
        this.expenseRepository = expenseRepository;
        this.authenticatedUserProvider = authenticatedUserProvider;
    }

    @Override
    public List<FindExpenseOutput> execute() {

        Long userId = authenticatedUserProvider.getAuthenticatedUserId();

        if (authenticatedUserProvider.isAuthenticatedUserByRole(UserRole.USER)){

            return this.expenseRepository.findByUserId(userId).stream()
                    .map(
                            (expense) -> new FindExpenseOutput(
                                    expense.getId(),
                                    expense.getName(),
                                    expense.getDescription(),
                                    expense.getAmount(),
                                    expense.getStatus(),
                                    expense.getDueDate(),
                                    expense.getPaymentDate(),
                                    expense.getCategory().getName()
                            )
                    )
                    .toList();
        }

        return this.expenseRepository.findAll().stream()
                .map(
                        (expense) -> new FindExpenseOutput(
                                expense.getId(),
                                expense.getName(),
                                expense.getDescription(),
                                expense.getAmount(),
                                expense.getStatus(),
                                expense.getDueDate(),
                                expense.getPaymentDate(),
                                expense.getCategory().getName()
                        )
                )
                .toList();

    }

}
