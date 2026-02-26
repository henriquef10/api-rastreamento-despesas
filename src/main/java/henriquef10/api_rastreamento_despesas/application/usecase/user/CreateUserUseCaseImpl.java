package henriquef10.api_rastreamento_despesas.application.usecase.user;

import henriquef10.api_rastreamento_despesas.core.entities.user.User;
import henriquef10.api_rastreamento_despesas.core.exception.user.UserAlreadyExistsException;
import henriquef10.api_rastreamento_despesas.core.usecases.user.create.CreateUserInput;
import henriquef10.api_rastreamento_despesas.core.usecases.user.create.CreateUserOutput;
import henriquef10.api_rastreamento_despesas.core.usecases.user.create.CreateUserUseCase;
import henriquef10.api_rastreamento_despesas.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository userRepository;

    public CreateUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CreateUserOutput execute(CreateUserInput input) {

        if(this.userRepository.findByLogin(input.login()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(input.password());

        User user = new User(
                input.name(),
                input.login(),
                encryptedPassword,
                input.role()
        );

        this.userRepository.save(user);

        return new CreateUserOutput(
                user.getId(),
                user.getName(),
                user.getLogin(),
                user.getRole()
        );

    }
}
