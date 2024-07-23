package com.project.todo.controller;

import com.project.todo.dto.TokenDto;
import com.project.todo.dto.request.ReqConnectedData;
import com.project.todo.dto.request.ReqRegister;
import com.project.todo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     * @param reqRegister
     * @return String
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody ReqRegister reqRegister) {
        int result = userService.register(reqRegister);
        return result == 0 ? ResponseEntity.ok().body("성공") : ResponseEntity.badRequest().body("실패");
    }

    /**
     * 로그인
     * @param reqRegister
     * @return TokenDto
     */
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody ReqRegister reqRegister) {
        TokenDto tokenDto = userService.login(reqRegister);
        return ResponseEntity.ok().body(tokenDto);
    }

    /**
     * 계정 연동
     * @return String
     */
    @PutMapping("/account/connected")
    public ResponseEntity<String> accountConnected(@RequestBody ReqConnectedData reqConnectedData) {
        int result = userService.accountConnected(reqConnectedData);
        return result == 0 ? ResponseEntity.ok().body("연동 성공") : ResponseEntity.badRequest().body("연동 실패");
    }
}
