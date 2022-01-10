package com.wego.cstms.persistence.repositories;

import com.wego.cstms.persistence.models.Course;
import com.wego.cstms.persistence.models.CourseContent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseContentRepository extends CrudRepository<CourseContent, Integer> {

//    @Query(value = "SELECT c FROM Course c ORDER BY c.views DESC")
//    List<Course> getTopAllTimes();

//    @Query(value = "SELECT c FROM Course c OR")
//    @Query(value = "SELECT c FROM Course c ORDER BY c.views DESC, c.updatedAt DESC")
//    List<Course> getTopTrending();


}
