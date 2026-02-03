package henriquef10.api_rastreamento_despesas.application.usecase.user;

import henriquef10.api_rastreamento_despesas.core.entities.user.User;
import henriquef10.api_rastreamento_despesas.core.usecases.user.CreateUserInput;
import henriquef10.api_rastreamento_despesas.core.usecases.user.CreateUserOutput;
import henriquef10.api_rastreamento_despesas.core.usecases.user.CreateUserUseCase;
import henriquef10.api_rastreamento_despesas.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

public class CreateUserService implements CreateUserUseCase {

    private final UserRepository userRepository;

    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CreateUserOutput execute(CreateUserInput input) {

        User user = new User(
                input.name(),
                input.login(),
                input.password()
        );

        this.userRepository.save(user);

        return new CreateUserOutput(
                user.getId(),
                user.getName()
        );

    }
}
