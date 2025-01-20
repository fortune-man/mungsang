package mungsang.mungsang.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import mungsang.mungsang.domain.dto.UserDto;
import mungsang.mungsang.domain.entity.User;
import mungsang.mungsang.domain.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.w3c.dom.UserDataHandler;

class UserMapperTest {

  @DisplayName("usermapper 테스트")
  @Test
  void testUserMapper(){
      //given
    UserEntity entity = new UserEntity();
    entity.setId(1L);
    entity.setUsername("김주형");
    entity.setEmail("wnguddl96@naver.com");

    UserDto userDto = UserMapper.toDto(entity);

    assertEquals(entity.getId(), userDto.getId());
    assertEquals(entity.getUsername(), userDto.getUsername());
    assertEquals(entity.getEmail(), userDto.getEmail());
  }

}