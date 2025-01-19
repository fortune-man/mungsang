package mungsang.mungsang.controller;

import mungsang.mungsang.domain.entity.User;
import mungsang.mungsang.domain.entity.UserEntity;
import mungsang.mungsang.domain.repository.UserRepository;
import mungsang.mungsang.domain.service.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {

    this.userRepository = userRepository;
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    // 도메인 객체 -> 엔티티 변환
    UserEntity userEntity = UserMapper.toEntity(user);
    UserEntity savedEntity = userRepository.save(userEntity);

    // 엔티티 -> 도메인 객체 반환
    User savedUser = UserMapper.toDomain(savedEntity);
    return ResponseEntity.ok(savedUser);
  }


  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    return userRepository.findById(id)
        .map(UserMapper::toDomain)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}
