package com.planet.develop.Entity;

import com.planet.develop.Enum.EcoEnum;
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
public class Expenditure extends BaseEntity {

    @Id
    private Long eno;

    @Column(nullable = false)
    private double cost;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId //@MapsId 는 @id로 지정한 컬럼에 @OneToOne 이나 @ManyToOne 관계를 매핑시키는 역할
    @JoinColumn(name = "eno")
    private ExpenditureDetail detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void update(double cost) {
        this.cost = cost;
    }

}
