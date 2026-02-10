package henriquef10.api_rastreamento_despesas.core.usecases.user.update;

import henriquef10.api_rastreamento_despesas.core.entities.user.User;

public interface UpdateUserUseCase {

    UpdateUserOutput execute(UpdateUserInput user);

}
