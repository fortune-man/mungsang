package mungsang.mungsang.domain.service;

import mungsang.mungsang.domain.dto.UserDto;
import mungsang.mungsang.domain.entity.User;
import mungsang.mungsang.domain.entity.UserEntity;

public class UserMapper {

  // userEntity -> userDto
  public static UserDto toDto(UserEntity userEntity) {
    return new UserDto(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail());
  }

  // userDto -> userEntity
  public static UserEntity toEntity(UserDto userDto) {
    return new UserEntity(userDto.getId(), userDto.getUsername(), userDto.getEmail());
  }


}
