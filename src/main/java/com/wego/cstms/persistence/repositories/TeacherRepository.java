package com.wego.cstms.persistence.repositories;

import com.wego.cstms.persistence.Entities.Teacher;
import com.wego.cstms.persistence.Entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeacherRepository extends CrudRepository<Teacher,Integer> {
    Optional<Teacher> findByUserName(String UserName);
    boolean existsByUserName(String UserName);

}
