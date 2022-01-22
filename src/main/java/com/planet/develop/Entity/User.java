package com.planet.develop.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column
    private int point;

//    @OneToMany(mappedBy = "user")
//    private List<Mission> missions= new ArrayList<>();

//    @OneToMany(mappedBy = "user")
//    private List<Income> incomes= new ArrayList<>();

}
