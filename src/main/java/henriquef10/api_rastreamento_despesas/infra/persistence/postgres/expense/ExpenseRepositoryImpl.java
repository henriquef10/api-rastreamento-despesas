package henriquef10.api_rastreamento_despesas.infra.persistence.postgres.expense;

import henriquef10.api_rastreamento_despesas.core.entities.expense.Expense;
import henriquef10.api_rastreamento_despesas.infra.persistence.postgres.expense.jpa.ExpenseJpaRepository;
import henriquef10.api_rastreamento_despesas.repository.ExpenseRepository;

import java.util.List;
import java.util.Optional;

public class ExpenseRepositoryImpl implements ExpenseRepository {

    private final ExpenseJpaRepository expenseJpaRepository;

    public ExpenseRepositoryImpl(ExpenseJpaRepository expenseJpaRepository){
        this.expenseJpaRepository = expenseJpaRepository;
    }


    @Override
    public List<Expense> findAll() {
        return this.expenseJpaRepository.findAll().stream().map(ExpenseMapper::toDomain).toList();
    }

    @Override
    public Optional<Expense> findById(Long id) {
        return this.expenseJpaRepository.findById(id).map(ExpenseMapper::toDomain);
    }

    @Override
    public List<Expense> findByUserId(Long userId) {
        return this.expenseJpaRepository.findAllByUserId(userId).stream().map(ExpenseMapper::toDomain).toList();
    }

    @Override
    public void save(Expense expense) {
        this.expenseJpaRepository.save(ExpenseMapper.toEntity(expense));
    }

    @Override
    public void delete(Long id) {
        this.expenseJpaRepository.deleteById(id);
    }

    @Override
    public void update(Expense expense) {
        this.expenseJpaRepository.save(ExpenseMapper.toEntity(expense));
    }

}
