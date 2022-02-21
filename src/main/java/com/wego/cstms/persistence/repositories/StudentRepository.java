package com.wego.cstms.persistence.repositories;

import com.wego.cstms.persistence.Entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Integer> {
    public Student findByUserName(String username);
    Boolean existsByUserName(String username);

}
