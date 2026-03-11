package henriquef10.api_rastreamento_despesas.application.usecase.user;

import henriquef10.api_rastreamento_despesas.application.exception.ForbiddenException;
import henriquef10.api_rastreamento_despesas.application.provider.AuthenticatedUserProvider;
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
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public UpdateUserUseCaseImpl(UserRepository userRepository, AuthenticatedUserProvider authenticatedUserProvider) {
        this.userRepository = userRepository;
        this.authenticatedUserProvider = authenticatedUserProvider;
    }

    @Override
    public UpdateUserOutput execute(UpdateUserInput input) {
        User user = this.userRepository.findById(input.id()).orElseThrow(() -> new UserNotFoundException("User not found"));

        if (authenticatedUserProvider.isAuthenticatedUserByRole(user.getRole())){
            if (!user.getId().equals(authenticatedUserProvider.getAuthenticatedUserId())) {
                throw new ForbiddenException("User not authorized to update this user");
            }
        }

        user.setName(input.name());
        user.setLogin(input.login());

        this.userRepository.update(user);

        return new UpdateUserOutput(user.getId(), user.getName(), user.getLogin());

    }
}
