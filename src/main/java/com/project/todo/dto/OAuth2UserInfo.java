package com.project.todo.dto;

import com.project.todo.entity.User;
import jakarta.security.auth.message.AuthException;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Map;

@Builder
@Slf4j
public record OAuth2UserInfo(String name, String email, String profile, String providerId, String provider) {

    public static OAuth2UserInfo of(String registrationId, Map<String, Object> attribute) {
        return switch (registrationId) {
            case "google" -> ofGoogle(attribute, registrationId);
            case "naver" -> ofNaver(attribute, registrationId);
            case "kakao" -> ofKakao(attribute, registrationId);
            default -> throw new UsernameNotFoundException("안돼 없어 돌아가");
        };
    }

    private static OAuth2UserInfo ofGoogle(Map<String, Object> attributes, String registrationId) {
        log.info("GOOGLE={}", attributes);
        return OAuth2UserInfo.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .providerId((String) attributes.get("sub"))
                .provider(registrationId)
                .build();
    }

    private static OAuth2UserInfo ofNaver(Map<String, Object> attributes, String registrationId) {
        log.info("NAVER={}", attributes);
        Map<String, Object> account = (Map<String, Object>) attributes.get("response");
        return OAuth2UserInfo.builder()
                .name((String) account.get("name"))
                .email((String) account.get("email"))
                .providerId((String) account.get("id"))
                .provider(registrationId)
                .build();

    }

    private static OAuth2UserInfo ofKakao(Map<String, Object> attributes, String registrationId) {
        log.info("KAKAO={}", attributes);
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");
        return OAuth2UserInfo.builder()
                .name((String) profile.get("nickname"))
                .email((String) account.get("email"))
                .providerId(String.valueOf(attributes.get("id")))
                .provider(registrationId)
                .build();
    }
}

