package com.planet.develop.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Mission {
    @Id @GeneratedValue
    @Column(name="mno")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private Long point;
    private boolean status;

//    public void changeMission(User user){
//        this.user=user;
//        user.getMissions().add(this);
//    }

}