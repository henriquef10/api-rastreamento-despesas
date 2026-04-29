package henriquef10.api_rastreamento_despesas.application.usecase.expense;

import henriquef10.api_rastreamento_despesas.core.dto.PageQuery;
import henriquef10.api_rastreamento_despesas.core.dto.PageResult;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.find.FindExpenseByUserIdUseCase;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.find.FindExpenseOutput;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.find.filters.ExpenseFilter;
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
    public PageResult<FindExpenseOutput> execute(Long userId, ExpenseFilter filter,  PageQuery pageQuery) {
        var page = this.expenseRepository.findByUserId(userId, filter, pageQuery);

        return new PageResult<FindExpenseOutput>(
                page.content().stream().map(
                        (expense) ->
                                new FindExpenseOutput(
                                        expense.getId(),
                                        expense.getName(),
                                        expense.getDescription(),
                                        expense.getAmount(),
                                        expense.getStatus(),
                                        expense.getDueDate(),
                                        expense.getPaymentDate(),
                                        expense.getCategory().getName()
                                )
                ).toList(),
                page.page(),
                page.size(),
                page.totalElements(),
                page.totalPages(),
                page.direction(),
                page.sortBy()
        );
    }
}
