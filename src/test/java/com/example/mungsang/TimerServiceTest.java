package com.example.mungsang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TimerServiceTest {

  @Autowired
  private TimerService timerService;

  @DisplayName("컨텍스트 로딩")
  @Test
  void contextLoads() {
    Assertions.assertNotNull(timerService);
  }

}
