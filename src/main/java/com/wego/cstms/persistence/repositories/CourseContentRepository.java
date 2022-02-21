package com.wego.cstms.persistence.repositories;

import com.wego.cstms.persistence.Entities.CourseContentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseContentRepository extends CrudRepository<CourseContentEntity, Integer> {

     List<CourseContentEntity> findByCourseId(Integer courseId);

}
