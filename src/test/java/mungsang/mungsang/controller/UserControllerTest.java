package mungsang.mungsang.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import mungsang.mungsang.domain.entity.User;
import mungsang.mungsang.domain.entity.UserEntity;
import mungsang.mungsang.domain.repository.UserRepository;
import mungsang.mungsang.domain.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;

  @Test
  void testCreateUser() throws Exception {
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