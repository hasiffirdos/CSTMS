package com.wego.cstms.persistence.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Entity
@Accessors(chain = true)
@Setter
@Getter
@AllArgsConstructor
//@NoArgsConstructor
@Table( name = "admins")
public class AdminEntity extends UserEntity {
//    @Id
//    private int    id;
//    private String firstname;
//    private String lastname;
//    private String email;
////    private String password;
//    private String phone;
//    private Date   dob;
//    private Date   signupDate;
//    private int    age;

//    @OneToOne
//    @JoinColumn(name = "id", referencedColumnName = "id")
//    private User user;

}