package henriquef10.api_rastreamento_despesas.repository;

import henriquef10.api_rastreamento_despesas.core.dto.PageQuery;
import henriquef10.api_rastreamento_despesas.core.dto.PageResult;
import henriquef10.api_rastreamento_despesas.core.entities.expense.Expense;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.find.filters.ExpenseFilter;
import org.hibernate.query.Page;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository {


    PageResult<Expense> findAll(ExpenseFilter filter, PageQuery pageQuery);
    Optional<Expense> findById(Long id);
    PageResult<Expense> findByUserId(Long userId, ExpenseFilter filter, PageQuery pageQuery);
    void save(Expense expense);
    void delete(Long id, Long userId);
    void delete(long id);
    void update(Expense expense);

}
