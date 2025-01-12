package mungsang.mungsang.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import mungsang.mungsang.domain.entity.User;
import mungsang.mungsang.domain.repository.UserRepository;
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

  @Test
  void testCreateUser() {
    //given
    User user = new User();
    user.setName("Mock User");
    user.setEmail("mock@example.com");
    when(userRepository.save(any(User.class))).thenReturn(user);

    //when
    User createdUser = userService.createUser(user);

    //then
    verify(userRepository, times(1)).save(any(User.class));
    assertEquals("Mock User", createdUser.getName());
    assertEquals("mock@example.com", createdUser.getEmail());
  }

}