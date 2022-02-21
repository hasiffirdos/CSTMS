package com.wego.cstms.persistence.repositories;

import com.wego.cstms.persistence.Entities.Teacher;
import com.wego.cstms.persistence.Entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {
    Optional<User> findByUserName(String UserName);
    Boolean existsByUserName(String UserName);
}
