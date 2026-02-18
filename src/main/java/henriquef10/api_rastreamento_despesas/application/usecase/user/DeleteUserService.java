package henriquef10.api_rastreamento_despesas.application.usecase.user;

import henriquef10.api_rastreamento_despesas.core.usecases.user.delete.DeleteUserUseCase;
import henriquef10.api_rastreamento_despesas.repository.UserRepository;

public class DeleteUserService implements DeleteUserUseCase {

    private final UserRepository userRepository;

    public DeleteUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(Long id) {

        this.userRepository.delete(id);

    }

}
