package com.sparta.springproject.controller;

import com.sparta.springproject.dto.schedule.ScheduleRequestDto;
import com.sparta.springproject.dto.schedule.ScheduleResponseDto;
import com.sparta.springproject.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> create(@RequestBody ScheduleRequestDto requestDto) {
        return ResponseEntity.ok(scheduleService.createSchedule(requestDto));
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAll() {
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.getSchedule(id));
    }
}
