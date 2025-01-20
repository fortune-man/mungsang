package mungsang.mungsang.domain.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import mungsang.mungsang.domain.dto.UserDto;
import mungsang.mungsang.domain.entity.UserEntity;
import mungsang.mungsang.domain.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @InjectMocks
  private UserService userService;

  @Mock
  private UserRepository userRepository;

  @DisplayName("서비스 사용자 생성 테스트")
  @Test
  void testCreateUser() {
    UserEntity entity = new UserEntity(null, "김주형", "wnguddl96@naver.com");
    when(userRepository.save(any(UserEntity.class))).thenReturn(entity);

    UserDto result = userService.createUser("김주형", "wnguddl96@naver.com");

    assertEquals("김주형", result.getUsername());
    assertEquals("wnguddl96@naver.com", result.getEmail());
  }

}