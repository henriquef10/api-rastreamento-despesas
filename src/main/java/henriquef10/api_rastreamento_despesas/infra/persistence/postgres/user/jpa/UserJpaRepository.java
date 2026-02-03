package henriquef10.api_rastreamento_despesas.infra.persistence.postgres.user.jpa;

import henriquef10.api_rastreamento_despesas.core.entities.user.User;
import henriquef10.api_rastreamento_despesas.infra.persistence.postgres.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByLogin(String login);
}
