package com.example.mungsang.controller;

import com.example.mungsang.service.TimerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/timer")
public class TimerController {

  // 타이머
  private final TimerService timerService;

  // 서비스 생성자 주입
  public TimerController(TimerService timerService) {
    this.timerService = timerService;
  }

  @PostMapping("/start")
  public ResponseEntity<String> startTimer(@RequestParam String userName) {
    timerService.startTimer(userName);
    return ResponseEntity.ok("멍 때리기" + userName);
  }
}
