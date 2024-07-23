package com.project.todo.entity;

import com.project.todo.config.security.Authority;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 키값

    @Column(name = "username")
    private String username; // 사용자 이름

    @Column(name = "password")
    private String password; // 비밀번호

    @Column(name = "email")
    private String email; // 이메일

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Authority role; // 권한

    @Column(name = "provider")
    private String provider; //oauth 플랫폼명

    @Column(name = "provider_id")
    private String providerId; //oauth id

    @Column(name = "connected")
    private Boolean connected; // 연동 여부

    @CreationTimestamp
    private Timestamp createDate; // 계정 생성 시간

    @PrePersist
    public void InitConnected() {
        this.connected = false;
    }
}