package com.wego.cstms.persistence.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int    id;
    private String userName;
    private String password;
    private boolean active;
    private String role;

}