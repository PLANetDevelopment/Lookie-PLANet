package com.planet.develop.Entity;

import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
public class Income {

    @Id @GeneratedValue
    private Long ino;

    private Long in_cost;

    @Enumerated(EnumType.STRING)
    private money_Way in_way;

    @Enumerated(EnumType.STRING)
    private money_Type in_type;

    private String memo;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public void addIncome(User user){
        this.user=user;
        user.getIncomes().add(this);
    }

}