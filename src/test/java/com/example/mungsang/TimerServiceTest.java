package com.example.mungsang;

import com.example.mungsang.service.TimerService;
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

  @DisplayName("사용자가 타이머를 시작하면 activeTimers에 기록이 생성된다")
  @Test
  void startTimerShouldAddToActiveTimers() {
    // given
    String userName = "어차피 망한 김주형";

    // when
    timerService.startTimer(userName);
    // then
    Assertions.assertTrue(timerService.getActiveTimers().containsKey(userName));
  }

  @DisplayName("사용자가 타이머를 종료하면 activeTimers에서 제거되고, 경과시간을 반환한다")
  @Test
  void stopTOmer_shouldRemoveFromActiveTimersAndReturnDuration() throws InterruptedException {
    // given
    String userName = "어차피 망할 레거시";
    timerService.startTimer(userName);
    long initialTime = System.currentTimeMillis();

    // (Optional) 타이머 실행 후 1초 대기
    Thread.sleep(1000);
    // when
    timerService.stopTimer(userName);

    // then
  }


}
