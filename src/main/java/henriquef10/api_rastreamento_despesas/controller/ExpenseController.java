package henriquef10.api_rastreamento_despesas.controller;

import henriquef10.api_rastreamento_despesas.controller.dto.ApiResponse;
import henriquef10.api_rastreamento_despesas.controller.dto.CreateExpenseRequest;
import henriquef10.api_rastreamento_despesas.controller.dto.UpdateExpenseRequest;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.DeleteExpenseUseCase;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.create.CreateExpenseInput;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.create.CreateExpenseOutput;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.create.CreateExpenseUseCase;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.find.FindAllExpenseUseCase;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.find.FindByIdExpenseUseCase;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.find.FindExpenseByUserIdUseCase;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.update.UpdateExpenseInput;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.update.UpdateExpenseOutput;
import henriquef10.api_rastreamento_despesas.core.usecases.expense.update.UpdateExpenseUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {


    private FindAllExpenseUseCase findAllExpenseUseCase;
    private FindByIdExpenseUseCase findByIdExpenseUseCase;
    private FindExpenseByUserIdUseCase findExpenseByUserIdUseCase;
    private CreateExpenseUseCase createExpenseUseCase;
    private UpdateExpenseUseCase updateExpenseUseCase;
    private DeleteExpenseUseCase deleteExpenseUseCase;

    public ExpenseController(FindAllExpenseUseCase findAllExpenseUseCase, FindByIdExpenseUseCase findByIdExpenseUseCase, FindExpenseByUserIdUseCase findExpenseByUserIdUseCase, CreateExpenseUseCase createExpenseUseCase, UpdateExpenseUseCase updateExpenseUseCase, DeleteExpenseUseCase deleteExpenseUseCase) {
        this.findAllExpenseUseCase = findAllExpenseUseCase;
        this.findByIdExpenseUseCase = findByIdExpenseUseCase;
        this.findExpenseByUserIdUseCase = findExpenseByUserIdUseCase;
        this.createExpenseUseCase = createExpenseUseCase;
        this.updateExpenseUseCase = updateExpenseUseCase;
        this.deleteExpenseUseCase = deleteExpenseUseCase;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findAll(){

        return ResponseEntity.ok(new ApiResponse(null, this.findAllExpenseUseCase.execute()));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){

        return ResponseEntity.ok(new ApiResponse(null, this.findByIdExpenseUseCase.execute(id)));

    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<ApiResponse> findByUserId(@PathVariable Long user_id){

        return ResponseEntity.ok(new ApiResponse(null, this.findExpenseByUserIdUseCase.execute(user_id)));

    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody CreateExpenseRequest request) {

        CreateExpenseOutput output = this.createExpenseUseCase.execute(
                new CreateExpenseInput(
                        request.user_id(),
                        request.name(),
                        request.description(),
                        request.amount(),
                        request.dueDate(),
                        request.paymentDate(),
                        request.category_id()
                )
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        "Despesa cadastrada com sucesso!",
                        output
                )
        );

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody UpdateExpenseRequest request){

        UpdateExpenseOutput output = this.updateExpenseUseCase.execute(new UpdateExpenseInput(
                id,
                request.name(),
                request.description(),
                request.amount(),
                request.dueDate(),
                request.paymentDate(),
                request.category_id()
        ));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                "Despesa atualizada com sucesso!",
                output
        ));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        this.deleteExpenseUseCase.execute(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>(
                "Despesa deletada com sucesso!",
                null
        ));
    }


}
