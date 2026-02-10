package henriquef10.api_rastreamento_despesas.infra.persistence.postgres.user;

import henriquef10.api_rastreamento_despesas.core.entities.user.User;
import henriquef10.api_rastreamento_despesas.core.exception.user.UserNotFoundException;
import henriquef10.api_rastreamento_despesas.infra.persistence.postgres.user.jpa.UserJpaRepository;
import henriquef10.api_rastreamento_despesas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;

    public UserRepositoryImpl(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<User> findAll() {
        return jpaRepository.findAll().stream().map(UserMapper::toDomain).toList();
    }

    @Override
    public Optional<User> findByLogin(String login) throws UserNotFoundException {
        Optional<UserEntity> entity = this.jpaRepository.findByLogin(login);

        return entity.map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<UserEntity> entity = this.jpaRepository.findById(id);

        return entity.map(UserMapper::toDomain);
    }

    @Override
    public void save(User user) {

        UserEntity entity = UserMapper.toEntity(user);
        this.jpaRepository.save(entity);

    }

    @Override
    public void delete(Long id) {
        this.jpaRepository.deleteById(id);
    }

    @Override
    public void update(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        this.jpaRepository.save(entity);
    }
}
