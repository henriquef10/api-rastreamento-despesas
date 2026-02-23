package henriquef10.api_rastreamento_despesas.repository;


import henriquef10.api_rastreamento_despesas.core.entities.category.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    List<Category> findAll();
    Optional<Category> findById(Long id);
    void save(Category category);
    void delete(Long id);
    void update(Category category);

}
