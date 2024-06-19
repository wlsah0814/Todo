package com.project.todo.dto;

import lombok.*;

@Builder
@ToString
@AllArgsConstructor
@Getter
@Setter
public class TokenDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
