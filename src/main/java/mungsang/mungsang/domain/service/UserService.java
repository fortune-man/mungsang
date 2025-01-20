package mungsang.mungsang.domain.service;

import mungsang.mungsang.domain.dto.UserDto;
import mungsang.mungsang.domain.dto.UserResponse;
import mungsang.mungsang.domain.entity.User;
import mungsang.mungsang.domain.entity.UserEntity;
import mungsang.mungsang.domain.repository.UserRepository;
import mungsang.mungsang.exception.ErrorCode;
import mungsang.mungsang.exception.NotFoundException;
import org.springframework.stereotype.Service;

// 핵심 비즈니스 로직 처리, UserRepository를 사용하여 데이터 접근 처리
@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  // 사용자 생성
  public UserDto createUser(String name, String email) {
    UserEntity userEntity = new UserEntity();
    userEntity.setUsername(name);
    userEntity.setEmail(email);

    UserEntity savedEntity = userRepository.save(userEntity);

    // 엔티티를 DTO로 변환
    return UserMapper.toDto(savedEntity);
  }

  // 사용자 조회
  private User toUser(UserEntity savedEntity) {
    return new User(savedEntity.getId(), savedEntity.getUsername(), savedEntity.getEmail());
  }

  // 사용자 조회
  public UserDto getUserById(Long id) {
    // request 본문에서 받은 user를 userDto로 return
    return userRepository.findById(id)
        .map(UserRepository::fromEntity)
        .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
  }


  // 사용자 변경
  public UserDto updateUser(Long id, String name, String email) {
    UserEntity userEntity = userRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

    userEntity.setUsername(name);
    userEntity.setEmail(email);

    UserEntity updatedEntity = userRepository.save(userEntity);

    return UserMapper.toDto(updatedEntity);
  }

  // 사용자 삭제
  public void deleteUser(Long id) {
    UserEntity userEntity = userRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

    userRepository.delete(userEntity);
  }



}
