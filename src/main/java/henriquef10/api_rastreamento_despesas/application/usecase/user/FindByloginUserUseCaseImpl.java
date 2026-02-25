package henriquef10.api_rastreamento_despesas.application.usecase.user;

import henriquef10.api_rastreamento_despesas.core.entities.user.User;
import henriquef10.api_rastreamento_despesas.core.exception.user.UserNotFoundException;
import henriquef10.api_rastreamento_despesas.core.usecases.user.find.FindByLoginUseCase;
import henriquef10.api_rastreamento_despesas.core.usecases.user.find.FindUserOutput;
import henriquef10.api_rastreamento_despesas.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindByloginUserUseCaseImpl implements FindByLoginUseCase {

    private final UserRepository userRepository;

    public FindByloginUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public FindUserOutput execute(String login) {
        Optional<User> user = this.userRepository.findByLogin(login);

        if(user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        return new FindUserOutput(user.get().getId(), user.get().getName(), user.get().getLogin());

    }
}
