package com.planet.develop.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@Data
@AllArgsConstructor
public class Result<T> {
    private T totalMoney;
    private T totalDetails;
    String content;
}
