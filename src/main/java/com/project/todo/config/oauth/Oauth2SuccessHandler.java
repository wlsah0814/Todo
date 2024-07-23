package com.project.todo.config.oauth;

import com.project.todo.config.jwt.TokenProvider;
import com.project.todo.config.security.Authority;
import com.project.todo.config.security.CustomUserDetails;
import com.project.todo.dto.OAuth2UserInfo;
import com.project.todo.dto.TokenDto;
import com.project.todo.entity.Oauth2User;
import com.project.todo.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
@RequiredArgsConstructor
public class Oauth2SuccessHandler implements AuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 Login Success");

        /* 1. User 객체 생성 */
        OAuth2UserInfo oAuth2UserInfo = (OAuth2UserInfo) authentication.getPrincipal();
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        User user = principal.getUser();

        log.info("oAuth2UserInfo={}", oAuth2UserInfo.toString());
        log.info("user={}", user.toString());

        /* 2. Token 생성 */
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        /*
        * if -> provider가 null 일 때는 -> 계정 연동여부 확인 페이지로 이동
        * else -> 이미 등록된 사용자로 처리하여 AccessToken, RefreshToken 모두 발행 -> /home 페이지 이동
        * */
        if(user.getProvider() == null && user.getProviderId() == null && !user.getConnected()) {
            String email = URLEncoder.encode(oAuth2UserInfo.email(), StandardCharsets.UTF_8);
            String provider = URLEncoder.encode(oAuth2UserInfo.provider(), StandardCharsets.UTF_8);
            String providerId = URLEncoder.encode(oAuth2UserInfo.providerId(), StandardCharsets.UTF_8);
            response.sendRedirect("http://localhost:3000/account/connected?" +
                    "email=" + email +
                    "&provider=" + provider +
                    "&providerId=" + providerId);
        } else {
            response.addHeader("Authorization", tokenDto.getAccessToken());
            response.addHeader("refresh", tokenDto.getRefreshToken());
            response.sendRedirect("http://localhost:3000/home");
        }
    }
}
