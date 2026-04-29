package henriquef10.api_rastreamento_despesas.infra.persistence.postgres.expense.specification;

import henriquef10.api_rastreamento_despesas.core.usecases.expense.find.filters.ExpenseFilter;
import henriquef10.api_rastreamento_despesas.infra.persistence.postgres.expense.ExpenseEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ExpenseFilterSpecification {

    public static Specification<ExpenseEntity> toSpecification(ExpenseFilter filter, Long UserId) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(UserId != null){
                predicates.add(
                        cb.equal(root.get("User").get("id"), UserId)
                );
            }

            if(filter.search() != null && !filter.search().trim().isEmpty()){
                predicates.add(cb.like(root.get("name").as(String.class), "%" + filter.search() + "%"));
            }

            if(filter.categoryId() != null){
                predicates.add(cb.equal(root.get("category").get("id"), filter.categoryId()));
            }

            if(filter.status() != null){
                predicates.add(cb.equal(root.get("status"), filter.status()));
            }

            if(filter.startDate() != null){
                predicates.add(cb.greaterThanOrEqualTo(root.get("dueDate"), filter.startDate()));
            }

            if(filter.endDate() != null){
                predicates.add(cb.lessThanOrEqualTo(root.get("dueDate"), filter.endDate()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));

        };
    }

}
