package mungsang.mungsang.domain.repository;

import java.util.Optional;
import mungsang.mungsang.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  // 이메일로 사용자 찾기
  Optional<User> findByEmail(String email);

}
