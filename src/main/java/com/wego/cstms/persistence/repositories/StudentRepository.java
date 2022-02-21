package com.wego.cstms.persistence.repositories;

import com.wego.cstms.persistence.Entities.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<StudentEntity,Integer> {
    public StudentEntity findByUserName(String username);
    Boolean existsByUserName(String username);

}
