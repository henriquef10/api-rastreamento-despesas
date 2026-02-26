package henriquef10.api_rastreamento_despesas.controller;

import henriquef10.api_rastreamento_despesas.controller.dto.ApiResponse;
import henriquef10.api_rastreamento_despesas.controller.dto.AuthenticationResponse;
import henriquef10.api_rastreamento_despesas.controller.dto.AuthenticationResquest;
import henriquef10.api_rastreamento_despesas.controller.dto.CreateUserRequest;
import henriquef10.api_rastreamento_despesas.core.entities.user.User;
import henriquef10.api_rastreamento_despesas.core.usecases.user.create.CreateUserInput;
import henriquef10.api_rastreamento_despesas.core.usecases.user.create.CreateUserOutput;
import henriquef10.api_rastreamento_despesas.core.usecases.user.create.CreateUserUseCase;
import henriquef10.api_rastreamento_despesas.infra.security.jwt.TokenService;
import henriquef10.api_rastreamento_despesas.infra.security.model.CustomUserDetails;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CreateUserUseCase createUserUseCase;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationResquest data) {

        var usernamepassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());

        var auth = this.authenticationManager.authenticate(usernamepassword);

        CustomUserDetails customUserDetail = (CustomUserDetails) auth.getPrincipal();

        var token = tokenService.generateToken(customUserDetail.getUser());

        return ResponseEntity.ok(
                new AuthenticationResponse(token)
        );
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid CreateUserRequest data){

        CreateUserOutput output = this.createUserUseCase.execute(new CreateUserInput(
                data.name(),
                data.login(),
                data.password(),
                data.role()
        ));

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        "Usuário cadastrado com sucesso!",
                        output
                )
        );

    }

}
    