package mungsang.mungsang.domain.service;

import mungsang.mungsang.domain.dto.UserResponse;
import mungsang.mungsang.domain.repository.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserResponse getUser(Long id) {
    return userRepository.findById(id)
        .map(UserRepository::fromEntity)
        .orElseThrow(() -> new NotFoundException("조회 되지 않는 회원입니다."));
  }

}
