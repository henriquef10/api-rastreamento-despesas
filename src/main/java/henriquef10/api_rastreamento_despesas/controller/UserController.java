package henriquef10.api_rastreamento_despesas.controller;


import henriquef10.api_rastreamento_despesas.controller.dto.CreateUserRequest;
import henriquef10.api_rastreamento_despesas.controller.dto.ApiResponse;
import henriquef10.api_rastreamento_despesas.controller.dto.UpdateUserRequest;
import henriquef10.api_rastreamento_despesas.core.usecases.user.create.CreateUserInput;
import henriquef10.api_rastreamento_despesas.core.usecases.user.create.CreateUserOutput;
import henriquef10.api_rastreamento_despesas.core.usecases.user.create.CreateUserUseCase;
import henriquef10.api_rastreamento_despesas.core.usecases.user.delete.DeleteUserUseCase;
import henriquef10.api_rastreamento_despesas.core.usecases.user.find.FindAllUserUseCase;
import henriquef10.api_rastreamento_despesas.core.usecases.user.find.FindByIdUserUseCase;
import henriquef10.api_rastreamento_despesas.core.usecases.user.find.FindByLoginUseCase;
import henriquef10.api_rastreamento_despesas.core.usecases.user.find.FindUserOutput;
import henriquef10.api_rastreamento_despesas.core.usecases.user.update.UpdateUserInput;
import henriquef10.api_rastreamento_despesas.core.usecases.user.update.UpdateUserOutput;
import henriquef10.api_rastreamento_despesas.core.usecases.user.update.UpdateUserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private FindAllUserUseCase findAllUserUseCase;
    private FindByIdUserUseCase findByIdUserUseCase;
    private FindByLoginUseCase findByLoginUseCase;
    private CreateUserUseCase createUserUseCase;
    private UpdateUserUseCase updateUserUseCase;
    private DeleteUserUseCase deleteUserUseCase;

    public UserController(FindAllUserUseCase findAllUserUseCase, FindByIdUserUseCase findByIdUserUseCase, FindByLoginUseCase findByLoginUseCase, CreateUserUseCase createUserUseCase, UpdateUserUseCase updateUserUseCase, DeleteUserUseCase deleteUserUseCase) {
        this.findAllUserUseCase = findAllUserUseCase;
        this.findByIdUserUseCase = findByIdUserUseCase;
        this.findByLoginUseCase = findByLoginUseCase;
        this.createUserUseCase = createUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findAll(){

        List<FindUserOutput> output = this.findAllUserUseCase.execute();

        return ResponseEntity.ok(new ApiResponse(
                null,
                output
        ));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        FindUserOutput output = this.findByIdUserUseCase.execute(id);

        return ResponseEntity.ok(new ApiResponse(
                null,
                output
        ));
    }


    /*@PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody CreateUserRequest request){

        CreateUserOutput output = this.createUserUseCase.execute(new CreateUserInput(
                request.name(),
                request.login(),
                request.password(),
                request.role()
        ));

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        "Usuário cadastrado com sucesso!",
                        output
                )
        );

    }*/

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@RequestBody UpdateUserRequest request, @PathVariable Long id){

        UpdateUserOutput output = this.updateUserUseCase.execute(new UpdateUserInput(
                id,
                request.name(),
                request.login()
        ));

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Usuário atualizado com sucesso!",
                        output
                )
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){

        this.deleteUserUseCase.execute(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ApiResponse<>(
                        "Usuário deletado com sucesso!",
                        null
                )
        );

    }


}
