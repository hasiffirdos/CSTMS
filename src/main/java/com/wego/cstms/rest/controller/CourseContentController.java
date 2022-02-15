package com.wego.cstms.rest.controller;

import com.wego.cstms.dto.models.CourseContentDto;
import com.wego.cstms.persistence.Entities.CourseContent;
import com.wego.cstms.service.ContentService;
import com.wego.cstms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses/{courseId}/course-contents")
public class CourseContentController {

    private final ContentService contentService;
    private final CourseService courseService;

    @Autowired
    public CourseContentController(ContentService contentService, CourseService courseService) {
        this.contentService = contentService;
        this.courseService = courseService;
    }


    @RequestMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CourseContentDto> getCoursesContent(@PathVariable int courseId){
        return contentService.getCoursesAllContents(courseId);
    }


//    TODO: do change this with uploading file functionality.
//    uploading course content
//


    @RequestMapping(method = RequestMethod.DELETE, value = "/{courseContentId}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('TEACHER') and  @principalSecurity.isCourseOwner(authentication,#courseId))")
    public void deleteCourseContent(@PathVariable Integer courseId, @PathVariable int courseContentId){
        contentService.deleteCourseContent(courseContentId);
    }






}
