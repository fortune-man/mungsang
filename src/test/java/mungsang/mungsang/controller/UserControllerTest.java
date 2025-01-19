package mungsang.mungsang.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import mungsang.mungsang.domain.entity.User;
import mungsang.mungsang.domain.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @Test
  void testCreateUser() throws Exception {
    // given
    User user = new User();
    user.setName("API user");
    user.setEmail("api@example.com");
    when(userService.createUser(any(User.class))).thenReturn(user);

    // when
    mockMvc.perform(post("/users"))
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\": \"API User\", \"email\": \"api@example.com\"}")
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").valie("API User"))
        .andExpect(jsonPath("$.email").value("api@example.com"));

    // then
  }
}