package com.kaadi.KadiBusinessCard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="active")
    private int active;

    @Column(name="linkedin")
    private String linkedin;

    @Column(name="facebook")
    private String facebook;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="user_role")
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private Set<Card> cards;
}
