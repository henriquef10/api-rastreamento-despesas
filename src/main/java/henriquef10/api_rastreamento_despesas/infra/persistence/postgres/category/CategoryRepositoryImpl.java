package henriquef10.api_rastreamento_despesas.infra.persistence.postgres.category;

import henriquef10.api_rastreamento_despesas.core.entities.category.Category;
import henriquef10.api_rastreamento_despesas.infra.persistence.postgres.category.jpa.CategoryJpaRepository;
import henriquef10.api_rastreamento_despesas.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

public class CategoryRepositoryImpl implements CategoryRepository {

    private CategoryJpaRepository categoryJpaRepository;

    public CategoryRepositoryImpl(CategoryJpaRepository categoryJpaRepository) {
        this.categoryJpaRepository = categoryJpaRepository;
    }


    @Override
    public List<Category> findAll() {
        return this.categoryJpaRepository.findAll().stream().map(CategoryMapper::toDomain).toList();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return this.categoryJpaRepository.findById(id).map(CategoryMapper::toDomain);
    }

    @Override
    public void save(Category category) {
        this.categoryJpaRepository.save(CategoryMapper.toEntity(category));
    }

    @Override
    public void delete(Long id) {
        this.categoryJpaRepository.deleteById(id);
    }

    @Override
    public void update(Category category) {
        this.categoryJpaRepository.save(CategoryMapper.toEntity(category));
    }
}
