package henriquef10.api_rastreamento_despesas.application.usecase.category;

import henriquef10.api_rastreamento_despesas.core.entities.category.Category;
import henriquef10.api_rastreamento_despesas.core.usecases.category.CategoryOutput;
import henriquef10.api_rastreamento_despesas.core.usecases.category.CreateCategoryInput;
import henriquef10.api_rastreamento_despesas.core.usecases.category.CreateCategoryUseCase;
import henriquef10.api_rastreamento_despesas.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateCategoryUseCaseImpl implements CreateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public CreateCategoryUseCaseImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryOutput execute(CreateCategoryInput input) {

        Category category = new Category(
                input.name(),
                input.description()
        );

        this.categoryRepository.save(category);

        return new CategoryOutput(category.getId(), category.getName(), category.getDescription());

    }
}
