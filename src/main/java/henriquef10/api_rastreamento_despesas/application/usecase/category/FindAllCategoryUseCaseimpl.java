package henriquef10.api_rastreamento_despesas.application.usecase.category;

import henriquef10.api_rastreamento_despesas.core.usecases.category.CategoryOutput;
import henriquef10.api_rastreamento_despesas.core.usecases.category.FindAllCategoryUseCase;
import henriquef10.api_rastreamento_despesas.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllCategoryUseCaseimpl implements FindAllCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public FindAllCategoryUseCaseimpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<CategoryOutput> execute() {
        return this.categoryRepository.findAll().stream()
                .map(category ->
                    new CategoryOutput(
                            category.getId(),
                            category.getName(),
                            category.getDescription()
                    )
                 )
                .toList();
    }
}
