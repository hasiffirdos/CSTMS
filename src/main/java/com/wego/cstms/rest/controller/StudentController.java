package com.wego.cstms.rest.controller;

import com.wego.cstms.dto.models.CourseContentDto;
import com.wego.cstms.persistence.Entities.Course;
import com.wego.cstms.dto.models.StudentDto;
import com.wego.cstms.service.ContentService;
import com.wego.cstms.service.FilesStorageService;
import com.wego.cstms.service.StudentService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final ContentService contentService;
    private final FilesStorageService filesStorageService;


//    @Autowired
    public StudentController(StudentService studentService, ContentService contentService, FilesStorageService filesStorageService) {
        this.studentService = studentService;
        this.contentService = contentService;
        this.filesStorageService = filesStorageService;
    }
//    OPEN
    @RequestMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public List<StudentDto> getStudents() {
        return studentService.getAllStudents();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register-student")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public void registerStudent(@RequestBody StudentDto studentDto) {
        studentService.addStudent(studentDto);
    }
//    GET Students Profile
    @RequestMapping(value = "/{studentId}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('STUDENT') and  @principalSecurity.hasUserId(authentication,#studentId))")
    public StudentDto getStudentById(@PathVariable Integer studentId) {
        return studentService.getStudent(studentId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{studentId}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('STUDENT') and  @principalSecurity.hasUserId(authentication,#studentId))")
    public void deactivateStudent(@PathVariable Integer studentId) {
        studentService.deleteStudent(studentId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{studentId}/courses/{courseId}/enroll")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('STUDENT') and  @principalSecurity.hasUserId(authentication,#studentId))")
    public void enrollCourse(@PathVariable int studentId, @PathVariable int courseId){
        studentService.addStudentCourse(studentId,courseId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{studentId}/courses/{courseId}/opt-out")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('STUDENT') and  @principalSecurity.hasUserId(authentication,#studentId))")
    public void optOutCourse(@PathVariable int studentId, @PathVariable int courseId){
        studentService.removeStudentCourse(studentId,courseId);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{studentId}/courses")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('STUDENT') and  @principalSecurity.hasUserId(authentication,#studentId))")
    public List<Course> getEnrolledCourses(@PathVariable int studentId){
        return studentService.getEnrolledCourses(studentId);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{studentId}/courses/{courseId}/course-content")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('STUDENT') and  @principalSecurity.hasEnrolledCourse(authentication,#studentId,#courseId))")
    public List<CourseContentDto> getEnrolledCoursesContent(@PathVariable int studentId, @PathVariable int courseId){
        return contentService.getCoursesAllContents(studentId);
    }
    @GetMapping("/{studentId}/courses/{courseId}/course-content/{courseContentId}/file/Download/")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('STUDENT') and  @principalSecurity.hasEnrolledCourse(authentication,#studentId,#courseId))")
    public ResponseEntity<Resource> getFile(@PathVariable int studentId,@PathVariable int courseId, @PathVariable int courseContentId) {
        String filename = contentService.getDownloadPath(courseId,courseContentId);
        Resource file = filesStorageService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=\"" + file.getFilename() + "\"").body(file);

    }
}
