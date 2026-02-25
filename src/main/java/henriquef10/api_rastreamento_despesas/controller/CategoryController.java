package henriquef10.api_rastreamento_despesas.controller;

import henriquef10.api_rastreamento_despesas.controller.dto.ApiResponse;
import henriquef10.api_rastreamento_despesas.controller.dto.CreateCategoryRequest;
import henriquef10.api_rastreamento_despesas.controller.dto.UpdateCategoryRequest;
import henriquef10.api_rastreamento_despesas.core.usecases.category.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private FindAllCategoryUseCase findAllCategoryUseCase;
    private CreateCategoryUseCase createCategoryUseCase;
    private UpdateCategoryUseCase updateCategoryUseCase;
    private DeleteCategoryUseCase deleteCategoryUseCase;

    public CategoryController(FindAllCategoryUseCase findAllCategoryUseCase, CreateCategoryUseCase createCategoryUseCase, UpdateCategoryUseCase updateCategoryUseCase, DeleteCategoryUseCase deleteCategoryUseCase) {
        this.findAllCategoryUseCase = findAllCategoryUseCase;
        this.createCategoryUseCase = createCategoryUseCase;
        this.updateCategoryUseCase = updateCategoryUseCase;
        this.deleteCategoryUseCase = deleteCategoryUseCase;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findAll(){

        return ResponseEntity.ok(new ApiResponse<>(null, this.findAllCategoryUseCase.execute()));

    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody CreateCategoryRequest request){

        CategoryOutput output = this.createCategoryUseCase.execute(new CreateCategoryInput(
                request.name(),
                request.description()
        ));

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                "Categoria criada com sucesso!",
                output
        ));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@RequestBody UpdateCategoryRequest request, @PathVariable Long id){

        CategoryOutput output = this.updateCategoryUseCase.execute(
                new CategoryInput(
                        id,
                        request.name(),
                        request.description()
                )
        );

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                "Categoria atualizada com sucesso!",
                output
        ));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        this.deleteCategoryUseCase.execute(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>(
                "Categoria deletada com sucesso!",
                null
        ));
    }


}
