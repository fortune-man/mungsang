package mungsang.mungsang.domain.entity;

// DB와 직접 매핑되는 JPA 엔티티 역할
public class UserEntity {

  private Long id;
  private String username;
  private String email;

  // JPA에서 필요한 기본 생성자
  public UserEntity() {}
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
