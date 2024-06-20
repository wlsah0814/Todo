package com.project.todo.dto;

import com.project.todo.entity.User;
import jakarta.security.auth.message.AuthException;
import lombok.Builder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Map;

@Builder
public record OAuth2UserInfo(String name, String email, String profile, String providerId, String provider) {

    public static OAuth2UserInfo of(String registrationId, Map<String, Object> attribute) {
        return switch (registrationId) {
            case "google" -> ofGoogle(attribute);
            case "naver" -> ofNaver(attribute);
            case "kakao" -> ofKakao(attribute);
            default -> throw new UsernameNotFoundException("안돼 없어 돌아가");
        };
    }

    private static OAuth2UserInfo ofGoogle(Map<String, Object> attributes) {
        return OAuth2UserInfo.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .providerId((String) attributes.get("sub"))
                .provider("google")
                .build();
    }

    private static OAuth2UserInfo ofNaver(Map<String, Object> attributes) {
        return OAuth2UserInfo.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .providerId((String) attributes.get("id"))
                .provider("naver")
                .build();
    }

    private static OAuth2UserInfo ofKakao(Map<String, Object> attributes) {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");
        return OAuth2UserInfo.builder()
                .name((String) profile.get("nickname"))
                .email((String) account.get("email"))
                .providerId((String) attributes.get("id"))
                .provider("kakao")
                .build();
    }
}