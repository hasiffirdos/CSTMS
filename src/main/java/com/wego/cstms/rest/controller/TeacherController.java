package com.wego.cstms.rest.controller;

import com.wego.cstms.persistence.models.Teacher;
import com.wego.cstms.rest.models.TeacherDto;
import com.wego.cstms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @RequestMapping("/teachers")
    public List<Teacher> getUsers(){
        return teacherService.getAllTeachers();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/teachers")
    public void registerTeacher(@RequestBody TeacherDto teacherDto){
        teacherService.addTeacher(teacherDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/teachers/{id}")
    public void unregisterUser(@PathVariable Integer id){
        teacherService.deleteUser(id);
    }


}
