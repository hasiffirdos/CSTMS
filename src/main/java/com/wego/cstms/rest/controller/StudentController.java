package com.wego.cstms.rest.controller;

import com.wego.cstms.dto.models.StudentDto;
import com.wego.cstms.dto.response.CustomResponse;
import com.wego.cstms.minio.MinioService;
import com.wego.cstms.service.ContentService;
import com.wego.cstms.service.StudentService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final ContentService contentService;
    private final MinioService minioService;


    //    @Autowired
    public StudentController(StudentService studentService, ContentService contentService, MinioService minioService) {
        this.studentService = studentService;
        this.contentService = contentService;
        this.minioService = minioService;
    }

    //    OPEN
    @RequestMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomResponse> getStudents() {

        return ResponseEntity.ok(CustomResponse.builder().payLoad(studentService.getAllStudents()).build());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register-student")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<CustomResponse> registerStudent(@RequestBody StudentDto studentDto) {
        try {
            return ResponseEntity.ok(CustomResponse.builder().payLoad(studentService.addStudent(studentDto)).build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(CustomResponse.builder().errorMessage(String.format("Duplicate Entry, User Already Exists with: %s", exception.getMessage())).build());
        }
    }

    //    GET Students Profile
    @RequestMapping(value = "/{studentId}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('STUDENT') and  @principalSecurity.hasUserId(authentication,#studentId))")
    public ResponseEntity<CustomResponse> getStudentById(@PathVariable Integer studentId) {
        StudentDto studentDto = studentService.getStudent(studentId);
        if (studentDto != null) {

            return ResponseEntity.ok(CustomResponse.builder().payLoad(studentDto).build());
        }

        return ResponseEntity.badRequest().body(CustomResponse.builder().errorMessage(String.format("Student with this Id:%d doesn't exist", studentId)).build());

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{studentId}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('STUDENT') and  @principalSecurity.hasUserId(authentication,#studentId))")
    public ResponseEntity<CustomResponse> deactivateStudent(@PathVariable Integer studentId) {
        Boolean deletedDone = studentService.deleteStudent(studentId);
        if (deletedDone) {
            return ResponseEntity.ok(CustomResponse.builder().payLoad(String.format("user Deleted with Id: %d", studentId)).build());
        } else {
            return ResponseEntity.badRequest().body(CustomResponse.builder().errorMessage(String.format("Student with this Id:%d doesn't exist", studentId)).build());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{studentId}/courses/{courseId}/enroll")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('STUDENT') and  @principalSecurity.hasUserId(authentication,#studentId))")
    public ResponseEntity<CustomResponse> enrollCourse(@PathVariable int studentId, @PathVariable int courseId) {
        try {

            return ResponseEntity.ok(CustomResponse.builder().payLoad(studentService.addStudentCourse(studentId, courseId)).build());

        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(CustomResponse.builder().errorMessage(exception.getMessage()).build());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{studentId}/courses/{courseId}/opt-out")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('STUDENT') and  @principalSecurity.hasUserId(authentication,#studentId))")
    public ResponseEntity<CustomResponse> optOutCourse(@PathVariable int studentId, @PathVariable int courseId) {
        try {
            return ResponseEntity.ok(CustomResponse.builder().payLoad(studentService.removeStudentCourse(studentId, courseId)).build());

        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(CustomResponse.builder().errorMessage(exception.getMessage()).build());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{studentId}/courses")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('STUDENT') and  @principalSecurity.hasUserId(authentication,#studentId))")
    public ResponseEntity<CustomResponse> getEnrolledCourses(@PathVariable int studentId) {
        return ResponseEntity.ok(CustomResponse.builder().payLoad(studentService.getEnrolledCourses(studentId)).build());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{studentId}/courses/{courseId}/course-content")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('STUDENT') and  @principalSecurity.hasEnrolledCourse(authentication,#studentId,#courseId))")
    public ResponseEntity<CustomResponse> getEnrolledCoursesContent(@PathVariable int studentId, @PathVariable int courseId) {
        return ResponseEntity.ok(CustomResponse.builder().payLoad(contentService.getCoursesAllContents(studentId)).build());
    }

    @GetMapping("/{studentId}/courses/{courseId}/course-content/{courseContentId}/file/Download/")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('STUDENT') and  @principalSecurity.hasEnrolledCourse(authentication,#studentId,#courseId))")
    public ResponseEntity<Resource> getFile(@PathVariable int studentId,
                                            @PathVariable Integer courseId,
                                            @PathVariable int courseContentId) {
        String[] fileMeta = contentService.getDownloadPath(courseId, courseContentId);

        Resource file = minioService.downloadObject(fileMeta[1]);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=\"" + fileMeta[0] + "\"").body(file);

    }
}
