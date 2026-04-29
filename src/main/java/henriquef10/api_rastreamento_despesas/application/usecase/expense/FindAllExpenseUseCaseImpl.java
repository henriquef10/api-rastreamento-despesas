package henriquef10.api_rastreamento_despesas.application.usecase.expense;

import henriquef10.api_rastreamento_despesas.application.provider.AuthenticatedUserProvider;
import henriquef10.api_rastreamento_despesas.core.dto.PageQuery;
import henriquef10.api_rastreamento_despesas.core.dto.PageResult;
import henriquef10.api_rastreamento_despesas.core.entities.expense.Expense;
import henriquef10.api_rastreamento_despesas.core.entities.user.UserRole;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.find.FindAllExpenseUseCase;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.find.FindExpenseOutput;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.find.filters.ExpenseFilter;
import henriquef10.api_rastreamento_despesas.infra.persistence.postgres.expense.ExpenseMapper;
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
    public PageResult<FindExpenseOutput> execute(ExpenseFilter  filter, PageQuery pageQuery) {

        Long userId = authenticatedUserProvider.getAuthenticatedUserId();

        PageResult<Expense> page;

        if (authenticatedUserProvider.isAuthenticatedUserByRole(UserRole.USER)){

            page = this.expenseRepository.findByUserId(userId, filter, pageQuery);

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

        page = this.expenseRepository.findAll(filter, pageQuery);

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
