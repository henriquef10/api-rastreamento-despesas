package henriquef10.api_rastreamento_despesas.application.usecase.user;

import henriquef10.api_rastreamento_despesas.core.entities.user.User;
import henriquef10.api_rastreamento_despesas.core.exception.user.UserNotFoundException;
import henriquef10.api_rastreamento_despesas.core.usecases.user.find.FindByIdUserUseCase;
import henriquef10.api_rastreamento_despesas.core.usecases.user.find.FindUserOutput;
import henriquef10.api_rastreamento_despesas.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindByIdUserUseCaseImpl implements FindByIdUserUseCase {

    private final UserRepository userRepository;

    public FindByIdUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public FindUserOutput execute(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found!"));

        return new FindUserOutput(user.getId(), user.getName(), user.getLogin());
    }

}
