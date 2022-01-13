package com.planet.develop.Entity;

import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenditureDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eno;

    @Column(name = "ex_way")
    private money_Way exWay;

    @Column(name = "ex_type")
    private money_Type exType;

    private String memo;

    @Column(name = "eco_detail")
    private String ecoDetail;

}
