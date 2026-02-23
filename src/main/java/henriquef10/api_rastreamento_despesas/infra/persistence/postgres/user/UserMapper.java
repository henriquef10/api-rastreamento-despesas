package henriquef10.api_rastreamento_despesas.infra.persistence.postgres.user;

import henriquef10.api_rastreamento_despesas.core.entities.user.User;

public class UserMapper {

    public static User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getLogin(),
                entity.getPassword(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();

        entity.setName(user.getName());
        entity.setLogin(user.getLogin());
        entity.setPassword(user.getPassword());
        entity.setCreatedAt(user.getCreatedAt());
        entity.setUpdatedAt(user.getUpdatedAt());

        return entity;
    }

}
