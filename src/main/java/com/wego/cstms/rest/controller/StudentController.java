package com.wego.cstms.rest.controller;

import com.wego.cstms.dto.models.CourseContentDto;
import com.wego.cstms.persistence.Entities.Course;
import com.wego.cstms.persistence.Entities.Student;
import com.wego.cstms.dto.models.StudentDto;
import com.wego.cstms.service.ContentService;
import com.wego.cstms.service.CourseService;
import com.wego.cstms.service.FilesStorageService;
import com.wego.cstms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final ContentService contentService;
    private final FilesStorageService filesStorageService;


    @Autowired
    public StudentController(StudentService studentService, CourseService courseService, ContentService contentService, FilesStorageService filesStorageService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.contentService = contentService;
        this.filesStorageService = filesStorageService;
    }
//    OPEN
    @RequestMapping("/students")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<StudentDto> getStudents() {
        return studentService.getAllStudents();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/students")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public void registerStudent(@RequestBody StudentDto studentDto) {
        studentService.addStudent(studentDto);
    }
//    GET Students Profile
    @RequestMapping(value = "/students/{id}")
    @PreAuthorize("hasAnyRole('ADMIN') or @principalSecurity.hasUserId(authentication,#id)")
    public StudentDto getStudentById(@PathVariable Integer id) {
        return studentService.getStudent(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/students/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT') or @principalSecurity.hasUserId(authentication,#id)")
    public void deactivateStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/students/{studentId}/courses/{courseId}/enroll")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')  or @principalSecurity.hasUserId(authentication,#studentId)")
    public void enrollCourse(@PathVariable int studentId, @PathVariable int courseId){
        studentService.addStudentCourse(studentId,courseId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/students/{studentId}/courses/{courseId}/opt-out")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')  or @principalSecurity.hasUserId(authentication,#studentId)")
    public void optOutCourse(@PathVariable int studentId, @PathVariable int courseId){
        studentService.removeStudentCourse(studentId,courseId);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/students/{studentId}/courses")
    @PreAuthorize("hasAnyRole('ADMIN') or @principalSecurity.hasUserId(authentication,#studentId)")
    public List<Course> getEnrolledCourses(@PathVariable int studentId){
        return studentService.getEnrolledCourses(studentId);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/students/{studentId}/courses/{courseId}/course-content")
    @PreAuthorize("hasAnyRole('ADMIN') or @principalSecurity.hasEnrolledCourse(authentication,#studentId,#courseId)")
    public List<CourseContentDto> getEnrolledCoursesContent(@PathVariable int studentId, @PathVariable int courseId){
        return contentService.getCoursesAllContents(studentId);
    }
    @GetMapping("/students/{studentId}/courses/{courseId}/course-content/{courseContentId}/file/Download/")
    @PreAuthorize("hasAnyRole('ADMIN') or @principalSecurity.hasEnrolledCourse(authentication,#studentId,#courseId)")
    public ResponseEntity<Resource> getFile(@PathVariable int studentId,@PathVariable int courseId, @PathVariable int courseContentId) {
        String filename = contentService.getDownloadPath(courseId,courseContentId);
        Resource file = filesStorageService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=\"" + file.getFilename() + "\"").body(file);

    }
}
