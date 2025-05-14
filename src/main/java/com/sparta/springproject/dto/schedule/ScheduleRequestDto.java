package com.sparta.springproject.dto.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class ScheduleRequestDto {
    private String title;
    private String content;
    private Long writerId;
}
