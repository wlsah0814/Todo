package com.project.todo.config.security;

public enum Authority {

    ROLE_GUEST("ROLE_GUEST"),
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    String role;

    Authority(String role) {
        this.role = role;
    }

    String getValue() {
        return role;
    }
}
