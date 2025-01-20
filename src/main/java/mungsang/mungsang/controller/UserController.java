package mungsang.mungsang.controller;

import mungsang.mungsang.domain.dto.UserDto;
import mungsang.mungsang.domain.entity.User;
import mungsang.mungsang.domain.entity.UserEntity;
import mungsang.mungsang.domain.repository.UserRepository;
import mungsang.mungsang.domain.service.UserMapper;
import mungsang.mungsang.domain.service.UserService;
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

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<UserDto> createUser(@RequestBody User user) {
    UserDto createdUser = userService.createUser(user.getName(), user.getEmail());
    return ResponseEntity.ok(createdUser);
  }


  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
    UserDto user = userService.getUserById(id);
    return ResponseEntity.ok(user);
  }
}
