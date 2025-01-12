package mungsang.mungsang;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationIntegerationTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testFullFlow() {
    mockMvc.perform(post("/users"))
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\": \"Integration User\", \"email\": \"integration@example.com\"}"))
    .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Integration User"));

    // Get user
    mockMvc.perform(get("/users/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Integration user"));
  }
}
