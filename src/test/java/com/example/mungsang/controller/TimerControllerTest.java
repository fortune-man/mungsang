package com.example.mungsang.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.mungsang.service.TimerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TimerController.class)
public class TimerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private TimerService timerService;

  @Test
  public void startTimerReturnMessage() throws Exception {
    mockMvc.perform(post("/timer/start")
            .param("userName", "김주형"))
        .andExpect(status().isOk())
        .andExpect(content().string(" 김주형 님 타이머 시작"));
  }
}
