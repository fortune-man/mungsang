package mungsang.mungsang.domain.service;

import mungsang.mungsang.domain.dto.UserResponse;
import mungsang.mungsang.domain.entity.User;
import mungsang.mungsang.domain.entity.UserEntity;
import mungsang.mungsang.domain.repository.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

// 핵심 비즈니스 로직 처리, UserRepository를 사용하여 데이터 접근 처리
@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  // 사용자 생성
  public User createUser(String name, String email) {
    UserEntity userEntity = new UserEntity();
    userEntity.setUsername(name);
    userEntity.setEmail(email);
    UserEntity savedEntity = userRepository.save(userEntity);

    return UserRepository.fromEntity(savedEntity);
  }

  // 사용자 조회
  public User getUserById(Long id) {
    return userRepository.findById(id)
        .map(UserRepository::fromEntity)
        .orElseThrow(() -> new NotFoundException("조회되지 않는 회원입니다."));
  }

  // 사용자 변경
  public User updateUser(Long id, String name, String email) {
    UserEntity userEntity = userRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("조회되지 않는 회원입니다."));

    userEntity.setUsername(name);
    userEntity.setEmail(email);
    UserEntity updatedEntity = userRepository.save(userEntity);

    return UserRepository.fromEntity(updatedEntity);
  }

  // 사용자 삭제
  public void deleteUser(Long id) {
    UserEntity userEntity = userRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("조회되지 않는 회원입니다."));

    userRepository.delete(userEntity);
  }



}
