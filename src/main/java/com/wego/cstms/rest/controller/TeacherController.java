package com.wego.cstms.rest.controller;

import com.wego.cstms.persistence.models.Course;
import com.wego.cstms.persistence.models.CourseContent;
import com.wego.cstms.persistence.models.Teacher;
import com.wego.cstms.rest.models.CourseContentDto;
import com.wego.cstms.rest.models.CourseDto;
import com.wego.cstms.rest.models.TeacherDto;
import com.wego.cstms.service.ContentService;
import com.wego.cstms.service.CourseService;
import com.wego.cstms.service.FilesStorageService;
import com.wego.cstms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class TeacherController {

    private final TeacherService teacherService;
    private final CourseService courseService;
    private final ContentService contentService;
    private final FilesStorageService filesStorageService;


    @Autowired
    public TeacherController(TeacherService teacherService,
                             CourseService courseService,
                             ContentService contentService,
                             FilesStorageService filesStorageService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.contentService = contentService;
        this.filesStorageService = filesStorageService;
    }

    @RequestMapping("/teachers")
    public List<Teacher> getTeachers() {
        return teacherService.getAllTeachers();
    }

    @RequestMapping(value = "/teachers/{id}")
    public Teacher getTeacherById(@PathVariable Integer id) {
        return teacherService.getTeacher(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/teachers")
    public void registerTeacher(@RequestBody TeacherDto teacherDto) {
        teacherService.addTeacher(teacherDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/teachers/{id}")
    public void deactivateTeacher(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);
    }

    @RequestMapping(value = "/teachers/{teacherId}/courses")
    public List<Course> getTeacherCourses(@PathVariable int teacherId){
        return teacherService.getTeacherCourses(teacherId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/teachers/{teacherId}/courses")
    public void addCourse(@RequestBody CourseDto courseDto, @PathVariable int teacherId){
        teacherService.addTeachersCourse(courseDto,teacherId);
        courseService.addCourse(courseDto);
    }


//    Course Content endpoints now on.

    @RequestMapping("/teachers/{teacherId}/courses/{courseId}/course-contents")
    public List<CourseContent> getCoursesContent(@PathVariable int teacherId, @PathVariable int courseId){

//        TODO: here, teacher id will be used for security...
        return contentService.getCoursesAllContents(courseId);
    }


    //    TODO: do change this with uploading file functionality.
//    uploading course content
    @RequestMapping( method = RequestMethod.POST, value = "/teachers/{teacherId}/courses/{courseId}/course-contents")
    public void addCourseContent(@RequestBody CourseContentDto courseContentDto,
                                 @PathVariable Integer courseId,
                                 @PathVariable int teacherId
                                 ){

        contentService.addCourseContent(courseContentDto, courseId);
    }
    @RequestMapping( method = RequestMethod.POST, value = "/teachers/{teacherId}/courses/{courseId}/course-contentsfile")
    public void addCourseContentwithfile(@RequestParam("name") String name,
                                 @RequestParam("content-file") MultipartFile file){

        try {
            filesStorageService.save(file);
        }catch (Exception e){
            e.printStackTrace();
        }
//        contentService.addCourseContent(courseContentDto, courseId);
    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/teachers/{teacherId}/courses/{courseId}/course-contents/{courseContentId}")
    public void deleteCourse(@PathVariable int courseId,
                             @PathVariable int teacherId,
                             @PathVariable int courseContentId){
        contentService.deleteCourseContent(courseContentId);
    }

//    @RequestMapping(method = RequestMethod.PUT, value = "/courses/{courseId}/course-content/")
//    public List<Student> updateCourseContent(@PathVariable int courseId){
//        return courseContentService.updateCourseContent(courseId);
//    }


}
