package com.planet.develop.Service;

import com.planet.develop.DTO.StatisticsDayDetailDto;
import com.planet.develop.DTO.StatisticsDto;
import com.planet.develop.Enum.TIE;

import java.util.List;

public interface StatisticsService {
    StatisticsDto CalculDif(String id, int month);
    List<StatisticsDayDetailDto> findDayDetail(String id, int month, TIE tie);
    StatisticsDto merge(String id, int month, TIE tie);
}
