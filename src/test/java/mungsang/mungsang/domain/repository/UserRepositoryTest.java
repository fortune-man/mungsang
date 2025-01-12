package mungsang.mungsang.domain.repository;

import static org.junit.jupiter.api.Assertions.*;

import mungsang.mungsang.domain.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 db 사용시
class UserRepositoryTest {
  @Autowired
  private UserRepository userRepository;

  @DisplayName("회원 저장 테스트")
  @Test
  public testSaveUser{
    //given
    User user = new User();
    user.setName("김주형");
    user.setEmail("test@example.com");

    //when
    User savedUser = userRepository.save(user);

    //then
    assertNotNull(savedUser.getId());
    assertEquals("김주형", savedUser.getName());
    assertEquals("wnguddl96@naver.com", savedUser.getEmaiL());
  }

}