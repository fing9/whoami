package com.dahhong.whoami.user.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "WAI_USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private AuthType authType;

    @Column(length = 100)
    private String name;

    @Column(length = 1000)
    private String profilePicture;

    public static User of(String id, AuthType authType, String name, String profilePicture) {
        return new User(id, authType, name, profilePicture);
    }

}
