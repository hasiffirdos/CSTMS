package com.wego.cstms.rest.controller;

import com.wego.cstms.persistence.Entities.Course;
import com.wego.cstms.persistence.Entities.CourseContent;
import com.wego.cstms.persistence.Entities.Teacher;
import com.wego.cstms.dto.models.CourseContentDto;
import com.wego.cstms.dto.models.CourseDto;
import com.wego.cstms.dto.models.TeacherDto;
import com.wego.cstms.service.ContentService;
import com.wego.cstms.service.CourseService;
import com.wego.cstms.service.FilesStorageService;
import com.wego.cstms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;
    private final CourseService courseService;
    private final ContentService contentService;
    private final FilesStorageService filesStorageService;


//    @Autowired
    public TeacherController(TeacherService teacherService,
                             CourseService courseService,
                             ContentService contentService,
                             FilesStorageService filesStorageService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.contentService = contentService;
        this.filesStorageService = filesStorageService;
    }

    //    OPEN
    @RequestMapping(method = RequestMethod.GET,value = "")
//    @PreAuthorize("hasRole('ADMIN')")
    public List<TeacherDto> getTeachers() {
        return teacherService.getAllTeachers();
    }


    @RequestMapping(method = RequestMethod.POST, value = "/register-teacher")
//    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public void registerTeacher(@RequestBody TeacherDto teacherDto) {
        teacherService.addTeacher(teacherDto);
    }

    @RequestMapping(value = "/{teacherId}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('TEACHER') and  @principalSecurity.hasUserId(authentication,#teacherId))")
    public TeacherDto getTeacherById(@PathVariable Integer teacherId) {
        return teacherService.getTeacher(teacherId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{teacherId}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('TEACHER') and  @principalSecurity.hasUserId(authentication,#teacherId))")
    public void deactivateTeacher(@PathVariable Integer teacherId) {
        teacherService.deleteTeacher(teacherId);
    }

    //    OPEN
    @RequestMapping(value = "/{teacherId}/courses")
//    @PreAuthorize("hasAnyRole('ADMIN','STUDENT','TEACHER')")
    public List<CourseDto> getTeacherCourses(@PathVariable int teacherId) {
        return teacherService.getTeacherCourses(teacherId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{teacherId}/courses")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public void addCourse(@RequestBody CourseDto courseDto, @PathVariable int teacherId) {
        teacherService.addTeachersCourse(courseDto, teacherId);
//        courseService.addCourse(courseDto);
    }


//    Course Content endpoints now on.

    @RequestMapping("/{teacherId}/courses/{courseId}/course-contents")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('TEACHER') and  @principalSecurity.hasUserId(authentication,#teacherId))")
    public List<CourseContentDto> getCoursesContent(@PathVariable int teacherId, @PathVariable int courseId) {

//        TODO: here, teacher id will be used for security... to check if current teacher own this course or not.
        return contentService.getCoursesAllContents(courseId);
    }


    //    TODO: do change this with uploading file functionality.
//    uploading course content
    @RequestMapping(method = RequestMethod.POST, value = "/{teacherId}/courses/{courseId}/course-contents")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('TEACHER') and  @principalSecurity.hasCourseOwnership(authentication,#teacherId,#courseId))")
    public void addCourseContent(@RequestBody CourseContentDto courseContentDto,
                                 @PathVariable Integer courseId,
                                 @PathVariable int teacherId
    ) {
        contentService.addCourseContent(courseContentDto, courseId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{teacherId}/courses/{courseId}/course-contents-file")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('TEACHER') and  @principalSecurity.hasCourseOwnership(authentication,#teacherId,#courseId))")
    public void addCourseContentWithFile(@RequestParam("file_name") String fileName,
                                         @RequestParam("file_type") String fileType,
                                         @RequestParam("description") String description,
                                         @RequestParam("file") MultipartFile contentFile,
                                         @PathVariable int courseId,
                                         @PathVariable int teacherId) {

        try {
            filesStorageService.save(contentFile, courseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CourseContentDto courseContentDto = new CourseContentDto();
        courseContentDto.setFileName(fileName);
        courseContentDto.setFileType(fileType);
        courseContentDto.setDescription(description);
        courseContentDto.setCreateAt(new Date());
        contentService.addCourseContent(courseContentDto, courseId);
    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{teacherId}/courses/{courseId}")
    @PreAuthorize("hasAnyRole('ADMIN') or @principalSecurity.hasCourseOwnership(authentication,#teacherId,#courseId) ")
    public void deleteCourse(@PathVariable int courseId,
                             @PathVariable int teacherId) {
        courseService.deleteCourse(courseId);
    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{teacherId}/courses/{courseId}/course-contents/{courseContentId}")
    @PreAuthorize("hasAnyRole('ADMIN') or @principalSecurity.hasCourseOwnership(authentication,#teacherId,#courseId) ")
    public void deleteCourseContent(@PathVariable int courseId,
                                    @PathVariable int teacherId,
                                    @PathVariable int courseContentId) {
        contentService.deleteCourseContent(courseContentId);
    }

//    @RequestMapping(method = RequestMethod.PUT, value = "/courses/{courseId}/course-content/")
//    public List<Student> updateCourseContent(@PathVariable int courseId){
//        return courseContentService.updateCourseContent(courseId);
//    }


}
