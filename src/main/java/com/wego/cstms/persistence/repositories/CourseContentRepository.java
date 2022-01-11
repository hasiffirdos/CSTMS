package com.wego.cstms.persistence.repositories;

import com.wego.cstms.persistence.models.CourseContent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseContentRepository extends CrudRepository<CourseContent, Integer> {

     List<CourseContent> findByCourseId(Integer courseId);

}
