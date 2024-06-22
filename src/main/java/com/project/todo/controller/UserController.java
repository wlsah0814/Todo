package com.project.todo.controller;

import com.project.todo.dto.TokenDto;
import com.project.todo.dto.request.ReqRegister;
import com.project.todo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok().body("apiTest");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody ReqRegister reqRegister) {
        int result = userService.register(reqRegister);
        return result == 0 ? ResponseEntity.ok().body("성공") : ResponseEntity.badRequest().body("실패");
    }
}
