package henriquef10.api_rastreamento_despesas.application.provider;

import henriquef10.api_rastreamento_despesas.core.entities.user.User;
import henriquef10.api_rastreamento_despesas.core.entities.user.UserRole;

public interface AuthenticatedUserProvider {

    Long getAuthenticatedUserId();
    String getAuthenticatedUserLogin();
    UserRole getAuthenticatedUserRole();
    boolean isAuthenticatedUserByRole(UserRole role);
    User getAuthenticatedUser();

}
