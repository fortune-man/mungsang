package mungsang.mungsang.domain.repository;

import java.util.Optional;
import mungsang.mungsang.domain.dto.UserDto;
import mungsang.mungsang.domain.entity.User;
import mungsang.mungsang.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


// jpa와 연결된 userentity 처리, 필요시 DTO로 변환
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  // 이메일로 사용자 찾기
  Optional<UserEntity> findByEmail(String email);

  // UserEntity를 User로 변환하는 유틸
  static UserDto fromEntity(UserEntity entity) {
    return new UserDto(entity.getId(), entity.getUsername(), entity.getEmail());
  }

}
