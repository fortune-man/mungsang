package com.example.mungsang.service;

import java.util.HashMap;
import java.util.Map;

public class TimerService {

  private final Map<String, Long> activeTimers = new HashMap<>();

  // 실시간 타이머 기록 return
  public Map<String, Long> getActiveTimers() {
    return activeTimers;
  }

  public void startTimer(String userName) {
    if (activeTimers.containsKey(userName)) {
      throw new IllegalStateException("이미 " + userName + "님의 타이머가 돌아가고 있습니다");
    }
    activeTimers.put(userName, System.currentTimeMillis());
  }

  // 타이머 중지
  public long stopTimer(String userName) {
    // 유효성 체크 : 유저명을 포함하지 않으면 실패
    if (!activeTimers.containsKey(userName)) {
      throw new IllegalStateException("타이머가 저장 실패" + userName);
    }
    // 현재 시간 - 시작시간
    Long startTime = activeTimers.remove(userName);
    return System.currentTimeMillis() - startTime;
  }

}
