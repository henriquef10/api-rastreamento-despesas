package henriquef10.api_rastreamento_despesas.application.usecase.user;

import henriquef10.api_rastreamento_despesas.core.entities.user.User;
import henriquef10.api_rastreamento_despesas.core.exception.user.UserNotFoundException;
import henriquef10.api_rastreamento_despesas.core.usecases.user.update.UpdateUserInput;
import henriquef10.api_rastreamento_despesas.core.usecases.user.update.UpdateUserOutput;
import henriquef10.api_rastreamento_despesas.core.usecases.user.update.UpdateUserUseCase;
import henriquef10.api_rastreamento_despesas.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserRepository userRepository;

    public UpdateUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UpdateUserOutput execute(UpdateUserInput input) {
        User user = this.userRepository.findById(input.id()).orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setName(input.name());
        user.setLogin(input.login());

        this.userRepository.update(user);

        return new UpdateUserOutput(user.getId(), user.getName(), user.getLogin());

    }
}
