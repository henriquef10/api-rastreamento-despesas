package henriquef10.api_rastreamento_despesas.application.usecase.expense;

import henriquef10.api_rastreamento_despesas.core.entities.expense.Expense;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.find.FindByIdExpenseUseCase;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.find.FindExpenseOutput;
import henriquef10.api_rastreamento_despesas.repository.ExpenseRepository;
import org.springframework.stereotype.Component;

@Component
public class FindByIdExpenseUseCaseImpl implements FindByIdExpenseUseCase {

    private final ExpenseRepository expenseRepository;

    public FindByIdExpenseUseCaseImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public FindExpenseOutput execute(Long id) {
        Expense expense = this.expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));

        FindExpenseOutput output = new FindExpenseOutput(
                expense.getId(),
                expense.getName(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getStatus(),
                expense.getDueDate(),
                expense.getPaymentDate(),
                expense.getCategory().getName()
        );

        return output;

    }

}
