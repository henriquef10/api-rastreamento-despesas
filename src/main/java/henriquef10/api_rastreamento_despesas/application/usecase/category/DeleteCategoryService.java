package henriquef10.api_rastreamento_despesas.application.usecase.category;

import henriquef10.api_rastreamento_despesas.core.entities.category.Category;
import henriquef10.api_rastreamento_despesas.core.usecases.category.CategoryOutput;
import henriquef10.api_rastreamento_despesas.core.usecases.category.CreateCategoryInput;
import henriquef10.api_rastreamento_despesas.core.usecases.category.CreateCategoryUseCase;
import henriquef10.api_rastreamento_despesas.core.usecases.category.DeleteCategoryUseCase;
import henriquef10.api_rastreamento_despesas.repository.CategoryRepository;

public class DeleteCategoryService implements DeleteCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public DeleteCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void execute(Long id) {
        this.categoryRepository.delete(id);
    }
}
