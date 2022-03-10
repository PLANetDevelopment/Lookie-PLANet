package com.planet.develop.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarDayDto {
    LocalDate date;
    Long incomeDays;
    Long ExpenditureDays;
    int ecoCount;
    int noneEcoCount;
}