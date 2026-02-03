package henriquef10.api_rastreamento_despesas.core.usecases.user;

import henriquef10.api_rastreamento_despesas.core.entities.user.User;

public interface CreateUserUseCase {

    CreateUserOutput execute(CreateUserInput input);

}
