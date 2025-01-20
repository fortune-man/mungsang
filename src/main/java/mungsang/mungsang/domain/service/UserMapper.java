package mungsang.mungsang.domain.service;

import mungsang.mungsang.domain.dto.UserDto;
import mungsang.mungsang.domain.entity.User;
import mungsang.mungsang.domain.entity.UserEntity;

public class UserMapper {

  // User -> UserEntity
  public static UserEntity toEntity(User user) {
    return new UserEntity(user.getId(), user.getName(), user.getEmail());
  }

  public static UserDto toDto(UserEntity userEntity) {
    return new UserDto(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail());
  }
}
