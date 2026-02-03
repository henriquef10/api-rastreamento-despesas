package henriquef10.api_rastreamento_despesas.repository;

import henriquef10.api_rastreamento_despesas.core.entities.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();
    Optional<User> findByLogin(String login);
    Optional<User> findById(Long id);
    void save(User user);
    void delete(Long id);
    void update(User user);

}
