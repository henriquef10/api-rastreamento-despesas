package henriquef10.api_rastreamento_despesas.infra.persistence.postgres.expense;

import henriquef10.api_rastreamento_despesas.core.entities.expense.Expense;

public class ExpenseMapper {

    public static ExpenseEntity toEntity(Expense domain) {
        ExpenseEntity entity = new ExpenseEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        entity.setAmount(domain.getAmount());
        entity.setDueDate(domain.getDueDate());
        entity.setStatus(domain.getStatus());
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
        return expense;

    }

}
