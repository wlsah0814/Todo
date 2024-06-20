package com.project.todo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

    @Column(name = "provider")
    private String provider; //oauth 플랫폼명

    @Column(name = "provider_id")
    private String providerId; //oauth id

    @CreationTimestamp
    private Timestamp createDate;
}