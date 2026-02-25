package henriquef10.api_rastreamento_despesas.application.usecase.expense;

import henriquef10.api_rastreamento_despesas.core.usecases.expense.find.FindExpenseByUserIdUseCase;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.find.FindExpenseOutput;
import henriquef10.api_rastreamento_despesas.repository.ExpenseRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindByUserIdExpenseUseCaseImpl implements FindExpenseByUserIdUseCase {

    private final ExpenseRepository expenseRepository;

    public FindByUserIdExpenseUseCaseImpl(ExpenseRepository expenseRepository) {
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
