package mungsang.mungsang.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import mungsang.mungsang.domain.entity.User;
import mungsang.mungsang.domain.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserMapperTest {

  @DisplayName("usermapper 테스트")
  @Test
  void testUserMapper(){
      //given
    UserEntity entity = new UserEntity();
    entity.setId(1L);
    entity.setUsername("김주형");
    entity.setEmail("wnguddl96@naver.com");

    User user = UserMapper.toDomain(entity);

    assertEquals(entity.getId(), user.getId());
    assertEquals(entity.getUsername(), user.getName());
    assertEquals(entity.getEmail(), user.getEmail());
  }

}