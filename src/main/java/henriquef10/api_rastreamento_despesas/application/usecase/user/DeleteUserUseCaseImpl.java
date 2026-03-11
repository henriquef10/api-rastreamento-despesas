package henriquef10.api_rastreamento_despesas.application.usecase.user;

import henriquef10.api_rastreamento_despesas.application.exception.ForbiddenException;
import henriquef10.api_rastreamento_despesas.application.provider.AuthenticatedUserProvider;
import henriquef10.api_rastreamento_despesas.core.entities.user.User;
import henriquef10.api_rastreamento_despesas.core.exception.user.UserNotFoundException;
import henriquef10.api_rastreamento_despesas.core.usecases.user.delete.DeleteUserUseCase;
import henriquef10.api_rastreamento_despesas.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepository userRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;


    public DeleteUserUseCaseImpl(UserRepository userRepository, AuthenticatedUserProvider authenticatedUserProvider) {
        this.userRepository = userRepository;
        this.authenticatedUserProvider = authenticatedUserProvider;
    }

    @Override
    public void execute(Long id) {

        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        if (authenticatedUserProvider.isAuthenticatedUserByRole(user.getRole())){
            if (!user.getId().equals(authenticatedUserProvider.getAuthenticatedUserId())) {
                throw new ForbiddenException("User not authorized to delete this user");
            }
        }

        this.userRepository.delete(id);

    }

}
