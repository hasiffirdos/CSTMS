package com.wego.cstms.persistence.repositories;

import com.wego.cstms.persistence.Entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity,Integer> {
    Optional<UserEntity> findByUserName(String UserName);
    Boolean existsByUserName(String UserName);
}
