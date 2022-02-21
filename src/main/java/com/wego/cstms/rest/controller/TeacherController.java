package com.wego.cstms.rest.controller;

import com.wego.cstms.dto.models.CourseContentDto;
import com.wego.cstms.dto.models.CourseDto;
import com.wego.cstms.dto.models.TeacherDto;
import com.wego.cstms.dto.response.CustomResponse;
import com.wego.cstms.minio.MinioService;
import com.wego.cstms.service.ContentService;
import com.wego.cstms.service.CourseService;
import com.wego.cstms.service.FilesStorageService;
import com.wego.cstms.service.TeacherService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Date;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;
    private final CourseService courseService;
    private final ContentService contentService;
    private final FilesStorageService filesStorageService;
    private final MinioService minioService;


    public TeacherController(TeacherService teacherService,
                             CourseService courseService,
                             ContentService contentService,
                             FilesStorageService filesStorageService, MinioService minioService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.contentService = contentService;
        this.filesStorageService = filesStorageService;
        this.minioService = minioService;
    }

    //    OPEN
    @RequestMapping(method = RequestMethod.GET, value = "")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomResponse> getAllTeachers() {
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .payLoad(teacherService.getAllTeachers())
                        .build()
        );
    }


    @RequestMapping(method = RequestMethod.POST, value = "/register-teacher")
//    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<CustomResponse> registerTeacher(@RequestBody TeacherDto teacherDto) {
        try {
            return ResponseEntity.ok(
                    CustomResponse.builder()
                            .payLoad(teacherService.addTeacher(teacherDto))
                            .build()
            );
        } catch (RuntimeException exception) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            CustomResponse.builder()
                                    .errorMessage(exception.getMessage())
                                    .build()
                    );
        }
    }

    @RequestMapping(value = "/{teacherId}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('TEACHER') and  @principalSecurity.hasUserId(authentication,#teacherId))")
    public ResponseEntity<CustomResponse> getTeacherById(@PathVariable Integer teacherId) {
        TeacherDto teacherDto = teacherService.getTeacher(teacherId);
        if (teacherDto != null) {
            return ResponseEntity.ok(
                    CustomResponse.builder()
                            .payLoad(teacherDto)
                            .build()
            );
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(
                            CustomResponse.builder()
                                    .errorMessage(String.format("Student with this Id:%d doesn't exist", teacherId))
                                    .build()
                    );
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{teacherId}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('TEACHER') and  @principalSecurity.hasUserId(authentication,#teacherId))")
    public ResponseEntity<CustomResponse> deactivateTeacher(@PathVariable Integer teacherId) {
        Boolean deletedDone = teacherService.deleteTeacher(teacherId);
        if (deletedDone) {
            return ResponseEntity.ok(
                    CustomResponse.builder()
                            .payLoad(String.format("user Deleted with Id: %d", teacherId))
                            .build()
            );
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(
                            CustomResponse.builder()
                                    .errorMessage(String.format("Teacher with this Id:%d doesn't exist", teacherId))
                                    .build()
                    );
        }
    }

    //    OPEN
    @RequestMapping(value = "/{teacherId}/courses")
//    @PreAuthorize("hasAnyRole('ADMIN','STUDENT','TEACHER')")
    public ResponseEntity<CustomResponse> getTeacherCourses(@PathVariable int teacherId) {
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .payLoad(teacherService.getTeacherCourses(teacherId))
                        .build()
        );
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{teacherId}/courses")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<CustomResponse> addCourse(@RequestBody CourseDto courseDto, @PathVariable int teacherId) {
        try {
            return ResponseEntity.ok(
                    CustomResponse.builder()
                            .payLoad(teacherService.addTeachersCourse(courseDto, teacherId))
                            .build()
            );
        } catch (RuntimeException exception) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            CustomResponse.builder()
                                    .errorMessage(exception.getMessage())
                                    .build()
                    );
        }
    }
    @RequestMapping("/{teacherId}/courses/{courseId}/course-contents")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('TEACHER') and  @principalSecurity.hasUserId(authentication,#teacherId))")
    public ResponseEntity<CustomResponse> getCoursesContent(@PathVariable int teacherId, @PathVariable int courseId) {
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .payLoad(contentService.getCoursesAllContents(courseId))
                        .build());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{teacherId}/courses/{courseId}/course-contents")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('TEACHER') and  @principalSecurity.hasCourseOwnership(authentication,#teacherId,#courseId))")
    public ResponseEntity<CustomResponse> addCourseContent(@RequestBody CourseContentDto courseContentDto,
                                                           @PathVariable Integer courseId,
                                                           @PathVariable int teacherId
    ) {
        return ResponseEntity.ok(
                CustomResponse.builder()
                .payLoad(contentService.addCourseContent(courseContentDto, courseId))
                .build());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{teacherId}/courses/{courseId}/course-contents-file")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('TEACHER') and  @principalSecurity.hasCourseOwnership(authentication,#teacherId,#courseId))")
    public ResponseEntity<CustomResponse> addCourseContentWithFile(
//            @RequestParam("file_name") String fileName,
//                                                                   @RequestParam("file_type") String fileType,
                                                                   @RequestParam("description") String description,
                                                                   @RequestParam("file") MultipartFile contentFile,
                                                                   @PathVariable Integer courseId,
                                                                   @PathVariable Integer teacherId) {
        String message = "";
        String fileName = contentFile.getOriginalFilename();
        String fileType = contentFile.getContentType();
        try {
//            filesStorageService.save(contentFile, courseId);
            message = minioService.putObject(contentFile, courseId.toString(), fileName,fileType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CourseContentDto courseContentDto = new CourseContentDto();
        courseContentDto.setFileName(fileName);
        courseContentDto.setFileType(fileType);
        courseContentDto.setDescription(description);
        courseContentDto.setCreateAt(new Date());
        contentService.addCourseContent(courseContentDto, courseId);
        return ResponseEntity.ok(
                CustomResponse.builder()
                .payLoad(String.format("File url: %s has been saved.", message))
                .build());
    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{teacherId}/courses/{courseId}")
    @PreAuthorize("hasAnyRole('ADMIN') or @principalSecurity.hasCourseOwnership(authentication,#teacherId,#courseId) ")
    public ResponseEntity<CustomResponse> deleteCourse(@PathVariable int courseId,
                                                       @PathVariable int teacherId) {
        try {
            return ResponseEntity.ok(CustomResponse.builder().payLoad(courseService.deleteCourse(courseId)).build());

        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(
                    CustomResponse.builder()
                            .errorMessage(exception.getMessage())
                            .build());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{teacherId}/courses/{courseId}/course-contents/{courseContentId}")
    @PreAuthorize("hasAnyRole('ADMIN') or @principalSecurity.hasCourseOwnership(authentication,#teacherId,#courseId) ")
    public ResponseEntity<CustomResponse> deleteCourseContent(@PathVariable int courseId,
                                                              @PathVariable int teacherId,
                                                              @PathVariable int courseContentId) {
        try {
            return ResponseEntity.ok(
                    CustomResponse.builder()
                            .payLoad(contentService.deleteCourseContent(courseId, courseContentId))
                            .build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(
                    CustomResponse.builder()
                            .errorMessage(exception.getMessage())
                            .build());
        }
    }

    @GetMapping("/{teacherId}/courses/{courseId}/course-content/{courseContentId}/file/Download/")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('TEACHER') and  @principalSecurity.hasCourseOwnership(authentication,#teacherId,#courseId))")
    public ResponseEntity<Resource> downloadContentFile(@PathVariable int teacherId,
                                                        @PathVariable Integer courseId,
                                                        @PathVariable int courseContentId) {
        String[] fileMeta = contentService.getDownloadPath(courseId, courseContentId);
        Resource file = minioService.downloadObject(fileMeta[1]);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=\"" + fileMeta[0] + "\"").body(file);

    }


}
