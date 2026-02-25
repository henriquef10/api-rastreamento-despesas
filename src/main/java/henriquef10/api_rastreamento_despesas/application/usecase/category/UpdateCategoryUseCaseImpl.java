package henriquef10.api_rastreamento_despesas.application.usecase.category;

import henriquef10.api_rastreamento_despesas.core.entities.category.Category;
import henriquef10.api_rastreamento_despesas.core.usecases.category.CategoryInput;
import henriquef10.api_rastreamento_despesas.core.usecases.category.CategoryOutput;
import henriquef10.api_rastreamento_despesas.core.usecases.category.UpdateCategoryUseCase;
import henriquef10.api_rastreamento_despesas.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UpdateCategoryUseCaseImpl implements UpdateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public UpdateCategoryUseCaseImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public CategoryOutput execute(CategoryInput input) {

        Category category = this.categoryRepository.findById(input.id()).orElseThrow(() -> new EntityNotFoundException("Category not found"));

        category.setName(input.name());
        category.setDescription(input.description());

        this.categoryRepository.save(category);

        return new CategoryOutput(category.getId(), category.getName(), category.getDescription());

    }
}
