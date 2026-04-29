package henriquef10.api_rastreamento_despesas.core.usecases.expense.find;

import henriquef10.api_rastreamento_despesas.core.dto.PageQuery;
import henriquef10.api_rastreamento_despesas.core.dto.PageResult;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.find.filters.ExpenseFilter;

public interface FindAllExpenseUseCase {

    PageResult<FindExpenseOutput> execute(ExpenseFilter filter, PageQuery pageQuery);

}
