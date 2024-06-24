package com.project.todo.config.oauth;

import com.project.todo.config.jwt.TokenProvider;
import com.project.todo.config.security.Authority;
import com.project.todo.config.security.CustomUserDetails;
import com.project.todo.dto.TokenDto;
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

@Component
@Slf4j
@RequiredArgsConstructor
public class Oauth2SuccessHandler implements AuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 Login Success");
        /* 1. User 객체 생성 */
        CustomUserDetails principalDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = principalDetails.getUser();
        log.info("user={}", user);

        /* 2. Token 생성 */
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        /*
        * if -> GUEST 일 때는 AccessToken 만 발행 -> 회원가입 페이지 이동
        * else -> 이미 등록된 사용자로 처리하여 AccessToken, RefreshToken 모두 발행
        * */
        if(user.getRole() == Authority.ROLE_GUEST) {
            response.addHeader("Authorization", tokenDto.getAccessToken());
            response.sendRedirect("http://localhost:3000/register");
        } else {
            response.addHeader("Authorization", tokenDto.getAccessToken());
            response.addHeader("refresh", tokenDto.getRefreshToken());
            response.sendRedirect("http://localhost:3000/home");
        }
    }
}
