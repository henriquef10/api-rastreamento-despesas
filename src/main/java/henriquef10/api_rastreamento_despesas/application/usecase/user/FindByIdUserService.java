package henriquef10.api_rastreamento_despesas.application.usecase.user;

import henriquef10.api_rastreamento_despesas.core.entities.user.User;
import henriquef10.api_rastreamento_despesas.core.exception.user.UserNotFoundException;
import henriquef10.api_rastreamento_despesas.core.usecases.user.find.FindByIdUserUseCase;
import henriquef10.api_rastreamento_despesas.core.usecases.user.find.FindUserOutput;
import henriquef10.api_rastreamento_despesas.repository.UserRepository;

import java.util.Optional;

public class FindByIdUserService implements FindByIdUserUseCase {

    private final UserRepository userRepository;

    public FindByIdUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public FindUserOutput execute(Long id) {
        Optional<User> user = this.userRepository.findById(id);

        if(user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        return new FindUserOutput(user.get().getId(), user.get().getName(), user.get().getLogin());
    }

}
