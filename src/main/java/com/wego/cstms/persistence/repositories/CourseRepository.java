package com.wego.cstms.persistence.repositories;

import com.wego.cstms.persistence.Entities.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course,Integer> {

    @Query(value = "SELECT c FROM Course c ORDER BY c.views DESC")
    List<Course> getTopAllTimes();


    @Query(value = "SELECT CSR.id, CSR.name, CSR.created_at,CSR.updated_at, CSR.price, CSR.rating,CSR.description, CSR.prerequisite, CSR.views\n" +
            "FROM courses CSR\n" +
            "JOIN enrollments EN ON CSR.id = EN.course_id\n" +
            "WHERE MONTH(EN.enrollment_date) = MONTH(CURRENT_DATE()) AND YEAR(EN.enrollment_date) = YEAR(CURRENT_DATE())\n" +
            "GROUP BY CSR.id\n" +
            "ORDER BY COUNT(EN.course_id) DESC;", nativeQuery = true)
    List<Course> getTopTrending();






}
