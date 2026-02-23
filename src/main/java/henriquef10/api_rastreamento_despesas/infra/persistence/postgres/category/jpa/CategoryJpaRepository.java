package henriquef10.api_rastreamento_despesas.infra.persistence.postgres.category.jpa;

import henriquef10.api_rastreamento_despesas.infra.persistence.postgres.category.CategoryEntity;
import henriquef10.api_rastreamento_despesas.repository.CategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long>{

}
