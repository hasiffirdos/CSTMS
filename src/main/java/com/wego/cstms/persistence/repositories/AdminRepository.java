package com.wego.cstms.persistence.repositories;

import com.wego.cstms.persistence.Entities.Admin;
import com.wego.cstms.persistence.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin,Integer> {
    public Admin findByUserName(String username);
}
