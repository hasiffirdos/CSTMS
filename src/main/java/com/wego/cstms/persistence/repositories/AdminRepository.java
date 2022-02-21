package com.wego.cstms.persistence.repositories;

import com.wego.cstms.persistence.Entities.AdminEntity;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<AdminEntity,Integer> {
    public AdminEntity findByUserName(String username);
    Boolean existsByUserName(String username);
}
