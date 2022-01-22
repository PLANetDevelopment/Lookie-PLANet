package com.planet.develop.Entity;

import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@NoArgsConstructor
@Entity
public class Income {

    @Id @GeneratedValue
    @Column(name="ino")
    private Long id;

    private Long in_cost;

    @Enumerated(EnumType.STRING)
    private money_Way in_way;

    @Enumerated(EnumType.STRING)
    private money_Type in_type;

    private String memo;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;


    @Builder
    public Income(Long in_cost,money_Way in_way,money_Type in_type,String memo,LocalDate date,User user){
        this.in_cost=in_cost;
        this.date=date;
        this.in_way=in_way;
        this.in_type=in_type;
        this.memo=memo;
        this.user=user;
    }

    public void update_income(Long in_cost,money_Way in_way,money_Type in_type,String memo,LocalDate date){
        this.in_cost=in_cost;
        this.date=date;
        this.in_way=in_way;
        this.in_type=in_type;
        this.memo=memo;
    }

}