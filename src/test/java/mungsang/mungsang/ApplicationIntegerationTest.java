package mungsang.mungsang;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import mungsang.mungsang.domain.entity.User;
import mungsang.mungsang.domain.entity.UserEntity;
import mungsang.mungsang.domain.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class ApplicationIntegrationTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private UserRepository userRepository;

  @DisplayName("전체 테스트 - 사용자 생성과 조회")
  @Test
  void testCreateAndGetUser() throws Exception {
    // 사용자 생성
    mockMvc.perform(post("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\": \"김주형\", \"email\": \"wnguddl96@naver.com\"}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.username").value("김주형"))
        .andExpect(jsonPath("$.email").value("wnguddl96@naver.com"));

    // 사용자 조회
    mockMvc.perform(get("/api/users/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.username").value("김주형"))
        .andExpect(jsonPath("$.email").value("wnguddl96@naver.com"));
  }
}