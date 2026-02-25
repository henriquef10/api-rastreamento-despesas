package henriquef10.api_rastreamento_despesas.application.usecase.category;

import henriquef10.api_rastreamento_despesas.core.usecases.category.DeleteCategoryUseCase;
import henriquef10.api_rastreamento_despesas.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteCategoryUseCaseImpl implements DeleteCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public DeleteCategoryUseCaseImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void execute(Long id) {
        this.categoryRepository.delete(id);
    }
}
