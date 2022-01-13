package com.wego.cstms.persistence.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wego.cstms.rest.models.TeacherDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    private String roles;

}