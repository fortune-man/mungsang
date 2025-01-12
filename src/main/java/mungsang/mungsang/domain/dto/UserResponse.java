package mungsang.mungsang.domain.dto;

import mungsang.mungsang.domain.entity.User;

public class UserResponse {
  private Long id;
  private String name;
  private String email;

  public static UserResponse fromEntity(User user) {
    UserResponse response = new UserResponse();
    response.id = user.getId();
    response.name = user.getName();
    response.email = user.getEmail();
    return response;
  }

}
