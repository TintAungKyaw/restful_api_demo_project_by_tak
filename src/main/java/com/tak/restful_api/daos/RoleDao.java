package com.tak.restful_api.daos;

import com.tak.restful_api.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Integer> {

    Role findByRole(String role);

}
