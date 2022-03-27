package com.planet.develop.Entity;

import com.planet.develop.Security.Enum.ClubMemberRole;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    // TODO: 추가
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<ClubMemberRole> roleSet = new HashSet<>();

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    private boolean fromSocial;

    // TODO: 추가
    public void addMemberRole(ClubMemberRole clubMemberRole) {
        roleSet.add(clubMemberRole);
    }

}
