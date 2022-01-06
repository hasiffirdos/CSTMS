package com.wego.cstms.persistence.repositories;

import com.wego.cstms.persistence.models.Student;
import com.wego.cstms.persistence.models.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Integer> {

}
