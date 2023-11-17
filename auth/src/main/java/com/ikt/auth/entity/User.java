package com.ikt.auth.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Entity
@Builder
@Table(name = "users_table")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String firstName;

    @Column(nullable = false)
    String lastName;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false)
    String password;
    @Enumerated(value = EnumType.STRING)
    Role role;

    public User(String firstName, String lastName, String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

}

