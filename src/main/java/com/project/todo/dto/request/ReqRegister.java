package com.project.todo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : com.project.todo.dto.request
 * fileName       : ReqRegister
 * author         : yangjinmo
 * date           : 6/22/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/22/24        yangjinmo       최초 생성
 */

@Getter
@AllArgsConstructor
public class ReqRegister {
    private String email;
    private String password;
}
