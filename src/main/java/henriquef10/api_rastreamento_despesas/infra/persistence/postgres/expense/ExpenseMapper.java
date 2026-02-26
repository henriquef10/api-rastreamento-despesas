package henriquef10.api_rastreamento_despesas.infra.persistence.postgres.expense;

import henriquef10.api_rastreamento_despesas.core.entities.expense.Expense;
import henriquef10.api_rastreamento_despesas.infra.persistence.postgres.category.CategoryMapper;
import henriquef10.api_rastreamento_despesas.infra.persistence.postgres.user.UserMapper;

public class ExpenseMapper {

    public static ExpenseEntity toEntity(Expense domain) {
        ExpenseEntity entity = new ExpenseEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        entity.setAmount(domain.getAmount());
        entity.setDueDate(domain.getDueDate());
        entity.setStatus(domain.getStatus());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        entity.setCategory(CategoryMapper.toEntity(domain.getCategory()));
        entity.setUser(UserMapper.toEntity(domain.getUser()));

        return entity;
    }

    public static Expense toDomain(ExpenseEntity entity){

        Expense expense = new Expense();
        expense.setId(entity.getId());
        expense.setName(entity.getName());
        expense.setDescription(entity.getDescription());
        expense.setAmount(entity.getAmount());
        expense.setDueDate(entity.getDueDate());
        expense.setStatus(entity.getStatus());
        expense.setCreatedAt(entity.getCreatedAt());
        expense.setUpdatedAt(entity.getUpdatedAt());
        expense.setCategory(CategoryMapper.toDomain(entity.getCategory()));
        expense.setUser(UserMapper.toDomain(entity.getUser()));

        return expense;

    }

}
