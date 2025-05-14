package com.sparta.springproject.dto.schedule;

import com.sparta.springproject.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long writerId;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.writerId = schedule.getWriterId();
    }
}
