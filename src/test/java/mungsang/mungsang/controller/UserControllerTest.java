package mungsang.mungsang.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import mungsang.mungsang.domain.dto.UserDto;
import mungsang.mungsang.domain.entity.User;
import mungsang.mungsang.domain.entity.UserEntity;
import mungsang.mungsang.domain.repository.UserRepository;
import mungsang.mungsang.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserControllerTest {


  @InjectMocks
  private UserController userController;

  @Mock
  private UserService userService;

  @BeforeEach
  void setUp() {
    userController = new UserController(userService);
  }

  @Test
  @DisplayName("컨트롤러 사용자 생성 테스트")
  void testCreateUser() throws Exception {
    UserDto mockUserDto = new UserDto(1L, "김주형", "wnguddl96@naver.com");

    // Mock 동작 정의
    when(userService.createUser("김주형", "wnguddl96@naver.com")).thenReturn(mockUserDto);

    ResponseEntity<UserDto> response = userController.createUser(
        new User(null, "김주형", "wnguddl96@naver.com")
    );

    // then
    assertEquals(200, response.getStatusCodeValue());
    assertEquals("김주형", response.getBody().getUsername());
    assertEquals("wnguddl96@naver.com", response.getBody().getEmail());
  }
}