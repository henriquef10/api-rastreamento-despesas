package henriquef10.api_rastreamento_despesas.infra.persistence.postgres.category;

import henriquef10.api_rastreamento_despesas.core.entities.category.Category;
import henriquef10.api_rastreamento_despesas.infra.persistence.postgres.expense.ExpenseEntity;

public class CategoryMapper {

    public static CategoryEntity toEntity(Category domain) {

        CategoryEntity entity = new CategoryEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());

        return entity;
    }

    public static Category toDomain(CategoryEntity entity) {
        return new Category(entity.getId(), entity.getName(), entity.getDescription());
    }

}
