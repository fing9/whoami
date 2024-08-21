package com.dahhong.whoami.user.domain.entity;

import com.dahhong.whoami.page.domain.entity.Page;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@Table(name = "WAI_USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private AuthType authType;

    @Column(length = 100, columnDefinition = "VARCHAR(100) CHARACTER SET UTF8")
    private String name;

    @Column(length = 1000)
    private String profilePicture;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Page> pages;

    public static User of(String id, Role role, AuthType authType, String name, String profilePicture) {
        return new User(id, role, authType, name, profilePicture, null);
    }

}
