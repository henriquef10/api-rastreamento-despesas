package henriquef10.api_rastreamento_despesas.core.usecases.expense.find;

import henriquef10.api_rastreamento_despesas.core.dto.PageQuery;
import henriquef10.api_rastreamento_despesas.core.dto.PageResult;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.find.filters.ExpenseFilter;

import java.util.List;

public interface FindExpenseByUserIdUseCase {

    PageResult<FindExpenseOutput> execute(Long userId, ExpenseFilter filter, PageQuery pageQuery);

}
