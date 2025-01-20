package mungsang.mungsang.domain.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import mungsang.mungsang.domain.dto.UserDto;
import mungsang.mungsang.domain.entity.User;
import mungsang.mungsang.domain.entity.UserEntity;
import mungsang.mungsang.domain.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


class UserServiceTest {

  @DisplayName("서비스 사용자 생성 테스트")
  @Test
  void testCreateUser() {
    UserRepository userRepository = Mockito.mock(UserRepository.class);
    UserService userService = new UserService(userRepository);

    UserEntity mockEntity = new UserEntity(1L, "김주형", "wnguddl96@naver.com");
    Mockito.when(userRepository.save(any(UserEntity.class))).thenReturn(mockEntity);

    UserDto createdUser = userService.createUser("김주형", "wnguddl96@naver.com");

    assertEquals("김주형", createdUser.getUsername());
    assertEquals("wnguddl96@naver.com", createdUser.getEmail());
  }

}