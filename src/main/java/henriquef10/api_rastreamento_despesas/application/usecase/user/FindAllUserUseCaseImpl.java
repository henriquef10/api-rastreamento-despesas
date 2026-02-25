package henriquef10.api_rastreamento_despesas.application.usecase.user;

import henriquef10.api_rastreamento_despesas.core.usecases.user.find.FindAllUserUseCase;
import henriquef10.api_rastreamento_despesas.core.usecases.user.find.FindUserOutput;
import henriquef10.api_rastreamento_despesas.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllUserUseCaseImpl implements FindAllUserUseCase {

    private final UserRepository userRepository;

    public FindAllUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<FindUserOutput> execute() {
         return this.userRepository
                 .findAll()
                 .stream()
                 .map(
                 user -> new FindUserOutput(
                                    user.getId(),
                                    user.getName(),
                                    user.getLogin())
                 )
                 .toList();
    }
}
