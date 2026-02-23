package henriquef10.api_rastreamento_despesas.core.usecases.expense.find;

import java.util.List;

public interface FindExpenseByUserIdUseCase {

    List<FindExpenseOutput> execute(Long userId);

}
