package com.project.todo.service.user;

import com.project.todo.config.jwt.TokenProvider;
import com.project.todo.config.security.Authority;
import com.project.todo.dto.TokenDto;
import com.project.todo.dto.request.ReqRegister;
import com.project.todo.entity.User;
import com.project.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.project.todo.service.user
 * fileName       : UserService
 * author         : yangjinmo
 * date           : 6/22/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/22/24        yangjinmo       최초 생성
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    /**
     * 회원가입
     * @param reqRegister
     * @return int
     */
    public int register(ReqRegister reqRegister) {
        if (userRepository.findByEmail(reqRegister.getEmail()).isEmpty()) {
            userRepository.save(
                    User.builder()
                            .email(reqRegister.getEmail())
                            .password(passwordEncoder.encode(reqRegister.getPassword()))
                            .role(Authority.ROLE_USER)
                            .build()
            );
            return 0;
        }
        return 1;
    }

    /**
     * 로그인
     * @param reqRegister
     * @return
     */
    public TokenDto login(ReqRegister reqRegister) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(reqRegister.getEmail(), reqRegister.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return tokenProvider.generateTokenDto(authentication);
    }
}
