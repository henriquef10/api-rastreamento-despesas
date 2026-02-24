package henriquef10.api_rastreamento_despesas.application.usecase.expense;

import henriquef10.api_rastreamento_despesas.core.usecases.expense.find.FindExpenseByUserIdUseCase;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.find.FindExpenseOutput;
import henriquef10.api_rastreamento_despesas.core.usecases.user.find.FindByIdUserUseCase;
import henriquef10.api_rastreamento_despesas.core.usecases.user.find.FindUserOutput;
import henriquef10.api_rastreamento_despesas.repository.ExpenseRepository;

import java.util.List;

public class FindByUserIdExpenseService implements FindExpenseByUserIdUseCase {

    private final ExpenseRepository expenseRepository;

    public FindByUserIdExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }


    @Override
    public List<FindExpenseOutput> execute(Long userId) {
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
}
