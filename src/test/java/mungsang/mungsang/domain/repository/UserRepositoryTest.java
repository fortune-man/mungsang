package mungsang.mungsang.domain.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import mungsang.mungsang.domain.entity.User;
import mungsang.mungsang.domain.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.ANY)
@ImportAutoConfiguration({JpaRepositoriesAutoConfiguration.class})
class UserRepositoryTest {
  @Autowired
  private UserRepository userRepository;

  @DisplayName("회원 저장 테스트")
  @Test
  void testSaveUser() {
    //given
    UserEntity userEntity = new UserEntity(1L, "김주형", "wnguddl96@naver.com");
    // when
    UserEntity savedEntity = userRepository.save(userEntity);


    //then
    assertNotNull(savedEntity.getId());
    assertEquals("김주형", savedEntity.getUsername());
    assertEquals("wnguddl96@naver.com", savedEntity.getEmail());
  }

  @DisplayName("id 조회")
  @Test
  void testFindById(){
    //given
    UserEntity userEntity = new UserEntity(1L, "김주형", "wnguddl96@naver.com");

    //when
    UserEntity savedUser = userRepository.save(userEntity);
    Optional<UserEntity> foundUser = userRepository.findById(savedUser.getId());

    //then
    assertNotNull(foundUser);
    assertEquals("김주형", savedUser.getUsername());
    assertEquals("wnguddl96@naver.com", savedUser.getEmail());
  }

}