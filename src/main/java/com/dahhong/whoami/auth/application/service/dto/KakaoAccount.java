package com.dahhong.whoami.auth.application.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KakaoAccount {

    private Boolean profile_needs_agreement;

    private Boolean profile_nickname_needs_agreement;

    private Boolean profile_image_needs_agreement;

    private Profile profile;

}
