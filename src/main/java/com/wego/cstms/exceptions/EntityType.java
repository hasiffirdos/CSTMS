package com.wego.cstms.exceptions;


public enum EntityType {
    TEACHER("Teacher"),
    STUDENT("Student"),
    COURSE("Course"),
    COURSE_CONTENT("Course Content"),
    ADMIN("Admin");


    String value;

    EntityType(String value){
        this.value = value;
    }

}
