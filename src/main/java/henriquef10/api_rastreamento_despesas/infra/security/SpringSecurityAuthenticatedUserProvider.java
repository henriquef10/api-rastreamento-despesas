package henriquef10.api_rastreamento_despesas.infra.security;

import henriquef10.api_rastreamento_despesas.application.exception.UnauthenticatedException;
import henriquef10.api_rastreamento_despesas.application.provider.AuthenticatedUserProvider;
import henriquef10.api_rastreamento_despesas.core.entities.user.User;
import henriquef10.api_rastreamento_despesas.core.entities.user.UserRole;
import henriquef10.api_rastreamento_despesas.infra.security.model.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SpringSecurityAuthenticatedUserProvider implements AuthenticatedUserProvider {


    @Override
    public Long getAuthenticatedUserId() {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth == null || !auth.isAuthenticated() || auth.getPrincipal() == null) {
            throw new UnauthenticatedException("User not authenticated");
        }

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        return userDetails.getUser().getId();
    }

    @Override
    public String getAuthenticatedUserLogin() {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth == null || !auth.isAuthenticated() || auth.getPrincipal() == null) {
            throw new UnauthenticatedException("User not authenticated");
        }

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        return userDetails.getUser().getLogin();
    }

    @Override
    public UserRole getAuthenticatedUserRole() {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth == null || !auth.isAuthenticated() || auth.getPrincipal() == null) {
            throw new UnauthenticatedException("User not authenticated");
        }

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        return userDetails.getUser().getRole();
    }

    @Override
    public boolean isAuthenticatedUserByRole(UserRole role) {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth == null || !auth.isAuthenticated() || auth.getPrincipal() == null) {
            throw new UnauthenticatedException("User not authenticated");
        }

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        return userDetails.getUser().getRole().equals(role);
    }

    @Override
    public User getAuthenticatedUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth == null || !auth.isAuthenticated() || auth.getPrincipal() == null) {
            throw new UnauthenticatedException("User not authenticated");
        }

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        return userDetails.getUser();
    }
}

