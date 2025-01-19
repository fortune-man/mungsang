package mungsang.mungsang.domain.repository;

import java.util.Optional;
import mungsang.mungsang.domain.entity.User;
import mungsang.mungsang.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
  // 이메일로 사용자 찾기
  Optional<User> findByEmail(String email);

  // UserEntity를 User로 변환하는 유틸
  static User fromEntity(UserEntity entity) {
    return new User(entity.getId(), entity.getUsername(), entity.getEmail());
  }

}
