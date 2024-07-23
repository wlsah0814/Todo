package com.project.todo.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReqConnectedData {
    String email;
    String provider;
    String providerId;
}
