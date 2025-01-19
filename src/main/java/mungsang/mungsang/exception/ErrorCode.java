package mungsang.mungsang.exception;

public enum ErrorCode {
  USER_NOT_FOUND("조회되지 않는 회원입니다."),
  INVALID_REQUEST("잘못된 요청입니다.");

  private final String message;

  ErrorCode(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
