package henriquef10.api_rastreamento_despesas.infra.persistence.postgres.expense;

import henriquef10.api_rastreamento_despesas.core.dto.PageQuery;
import henriquef10.api_rastreamento_despesas.core.dto.PageResult;
import henriquef10.api_rastreamento_despesas.core.entities.expense.Expense;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.find.filters.ExpenseFilter;
import henriquef10.api_rastreamento_despesas.infra.persistence.postgres.expense.jpa.ExpenseJpaRepository;
import henriquef10.api_rastreamento_despesas.infra.persistence.postgres.expense.specification.ExpenseFilterSpecification;
import henriquef10.api_rastreamento_despesas.repository.ExpenseRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ExpenseRepositoryImpl implements ExpenseRepository {

    private final ExpenseJpaRepository expenseJpaRepository;

    public ExpenseRepositoryImpl(ExpenseJpaRepository expenseJpaRepository){
        this.expenseJpaRepository = expenseJpaRepository;
    }


    @Override
    public PageResult<Expense> findAll(ExpenseFilter filter, PageQuery query) {

        Pageable pageable = PageRequest.of(
                query.page(),
                query.size(),
                Sort.by(
                        Sort.Direction.fromString(query.direction()),
                        query.sortBy()
                )
        );

        Specification<ExpenseEntity> spec = ExpenseFilterSpecification.toSpecification(filter, null);

        Page<ExpenseEntity> page = expenseJpaRepository.findAll(spec, pageable);

        List<Expense> expenses = page.getContent().stream().map(ExpenseMapper::toDomain).toList();

        return new PageResult<Expense>(
                expenses,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                query.sortBy(),
                query.direction()
                );
    }

    @Override
    public Optional<Expense> findById(Long id) {
        return this.expenseJpaRepository.findById(id).map(ExpenseMapper::toDomain);
    }

    @Override
    public PageResult<Expense> findByUserId(Long userId, ExpenseFilter filter, PageQuery query) {

        Pageable pageable = PageRequest.of(
                query.page(),
                query.size(),
                Sort.by(
                        Sort.Direction.fromString(query.direction()),
                        query.sortBy()
                )
        );

        Specification<ExpenseEntity> spec = ExpenseFilterSpecification.toSpecification(filter, userId);

        Page<ExpenseEntity> page = expenseJpaRepository.findAll(spec, pageable);

        List<Expense> expenses = page.getContent().stream().map(ExpenseMapper::toDomain).toList();

        return new PageResult<Expense>(
                expenses,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                query.sortBy(),
                query.direction()
        );

    }

    @Override
    public void save(Expense expense) {
        this.expenseJpaRepository.save(ExpenseMapper.toEntity(expense));
    }

    @Override
    @Transactional
    public void delete(Long id, Long userId) {
        this.expenseJpaRepository.deleteByIdAndUser_Id(id, userId);
    }

    @Override
    public void delete(long id) {
        this.expenseJpaRepository.deleteById(id);
    }

    @Override
    public void update(Expense expense) {
        this.expenseJpaRepository.save(ExpenseMapper.toEntity(expense));
    }

}
