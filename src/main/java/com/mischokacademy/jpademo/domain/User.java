package com.mischokacademy.jpademo.domain;

import lombok.Data;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password_Encoded")
    private String passwordEncoded;

    @OneToMany(mappedBy = "user")
    private List<Attendance> attendances;
}
