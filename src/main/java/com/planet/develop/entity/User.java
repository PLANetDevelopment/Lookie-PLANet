package com.planet.develop.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {
    @Id @GeneratedValue
    @Column(name="user_id")
    private Long id;

    private String name;

    private int point;

    @OneToMany(mappedBy = "user")
    private List<Mission> missions= new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Income> incomes= new ArrayList<>();
}
