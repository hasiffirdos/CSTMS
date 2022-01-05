package com.wego.cstms.persistence.repositories;

import com.wego.cstms.persistence.models.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course,Integer> {

}
