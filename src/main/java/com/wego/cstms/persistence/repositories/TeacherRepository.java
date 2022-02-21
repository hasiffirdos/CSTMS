package com.wego.cstms.persistence.repositories;

import com.wego.cstms.persistence.Entities.TeacherEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeacherRepository extends CrudRepository<TeacherEntity,Integer> {
    Optional<TeacherEntity> findByUserName(String UserName);
    boolean existsByUserName(String UserName);

}
