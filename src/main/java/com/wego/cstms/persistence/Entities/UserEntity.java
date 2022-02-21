package com.wego.cstms.persistence.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int    id;
    private String userName;
    private String password;
    private boolean active;
    private String role;
    private String firstname;
    private String lastname;
    private String email;
    //    private String password;
    private String phone;
    private Date dob;
    private Date   signupDate;
    private int    age;


}