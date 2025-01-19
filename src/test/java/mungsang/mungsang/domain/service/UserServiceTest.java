package mungsang.mungsang.domain.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import mungsang.mungsang.domain.entity.User;
import mungsang.mungsang.domain.entity.UserEntity;
import mungsang.mungsang.domain.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;

  @DisplayName("사용자 생성")
  @Test
  void testCreateUser() {
    UserEntity userEntity = new UserEntity();
    userEntity.setUsername("김주형");
    userEntity.setEmail("wnguddl96@naver.com");

    when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

    User createdUser = userService.createUser("김주형", "wnguddl96@naver.com");

    assertNotNull(createdUser);
    assertEquals("김주형", createdUser.getName());
    assertEquals("wnguddl96@naver.com", createdUser.getEmail());
  }

}