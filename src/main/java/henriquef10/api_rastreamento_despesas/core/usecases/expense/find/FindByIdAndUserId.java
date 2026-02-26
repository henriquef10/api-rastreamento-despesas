package henriquef10.api_rastreamento_despesas.core.usecases.expense.find;

import henriquef10.api_rastreamento_despesas.core.entities.expense.Expense;

import java.util.Optional;

public interface FindByIdAndUserId {
    Optional<Expense> execute(Long id, Long userId);
}
