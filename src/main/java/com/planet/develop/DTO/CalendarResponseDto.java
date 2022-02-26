package com.planet.develop.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarResponseDto {
    List<LocalDate> anniversaryList;
    CalendarDto calendarDto;
    String content;
}