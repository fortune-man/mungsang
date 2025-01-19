package mungsang.mungsang.domain.service;

import mungsang.mungsang.domain.entity.User;
import mungsang.mungsang.domain.entity.UserEntity;

public class UserMapper {
  public static User toDomain(UserEntity entity) {
    return new User(entity.getId(), entity.getUsername(), entity.getEmail());
  }

  public static UserEntity toEntity(User user) {
    UserEntity entity = new UserEntity();
    entity.setId(user.getId());
    entity.setUsername(user.getName());
    entity.setEmail(user.getEmail());
    return entity;
  }

}
