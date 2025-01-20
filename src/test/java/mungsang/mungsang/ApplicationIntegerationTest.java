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

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationIntegrationTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private UserRepository userRepository;

  @DisplayName("전체 테스트 - 사용자 생성과 조회")
  @Test
  void testFullFlow() throws Exception {
    // 사용자 생성
    mockMvc.perform(post("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\": \"김주형\", \"email\": \"wnguddl96@naver.com\"}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.username").value("김주형"))
        .andExpect(jsonPath("$.email").value("wnguddl96@naver.com"));

    // 생성된 사용자가 데이터베이스에 저장되었는지 확인
    UserEntity savedUser = userRepository.findByEmail("wnguddl96@naver.com").orElseThrow();
    Assertions.assertThat(savedUser.getUsername()).isEqualTo("김주형");

    // 사용자 조회 요청
    mockMvc.perform(get("/api/users/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.username").value("김주형"))
        .andExpect(jsonPath("$.email").value("wnguddl96@naver.com"));
  }
}