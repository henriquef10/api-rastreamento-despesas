package henriquef10.api_rastreamento_despesas.repository;

import henriquef10.api_rastreamento_despesas.core.entities.expense.Expense;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository {


    List<Expense> findAll();
    Optional<Expense> findById(Long id);
    List<Expense> findByUserId(Long userId);
    void save(Expense expense);
    void delete(Long id, Long userId);
    void delete(long id);
    void update(Expense expense);

}
