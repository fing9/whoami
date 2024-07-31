package com.dahhong.whoami.auth.application.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

    private String nickname;

    private String thumbnail_image_url;

    private String profile_image_url;

    private Boolean is_default_image;

    private Boolean is_default_nickname;

}
