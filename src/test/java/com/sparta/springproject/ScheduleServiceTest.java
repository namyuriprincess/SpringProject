package com.sparta.springproject;


import com.sparta.springproject.dto.schedule.ScheduleRequestDto;
import com.sparta.springproject.dto.schedule.ScheduleResponseDto;
import com.sparta.springproject.repository.ScheduleRepository;
import com.sparta.springproject.service.ScheduleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ScheduleServiceTest {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Test
    @DisplayName("일정 등록 성공")
    void createSchedule_success() {
        // given
        ScheduleRequestDto request = new ScheduleRequestDto();
        request.setTitle("회의 일정");
        request.setContent("프로젝트 회의 진행");
        request.setWriterId(1L);

        // when
        ScheduleResponseDto response = scheduleService.createSchedule(request);

        // then
        assertThat(response.getTitle()).isEqualTo("회의 일정");
        assertThat(response.getWriterId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("전체 일정 목록 조회")
    void getAllSchedules_success() {
        // when
        List<ScheduleResponseDto> schedules = scheduleService.getAllSchedules();

        // then
        assertThat(schedules).isNotNull();
        assertThat(schedules.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @DisplayName("단일 일정 조회 성공")
    void getSchedule_success() {
        // given
        ScheduleRequestDto request = new ScheduleRequestDto();
        request.setTitle("조회 테스트 일정");
        request.setContent("조회 테스트 내용");
        request.setWriterId(2L);
        ScheduleResponseDto created = scheduleService.createSchedule(request);

        // when
        ScheduleResponseDto found = scheduleService.getSchedule(created.getId());

        // then
        assertThat(found.getId()).isEqualTo(created.getId());
        assertThat(found.getTitle()).isEqualTo("조회 테스트 일정");
    }

}
