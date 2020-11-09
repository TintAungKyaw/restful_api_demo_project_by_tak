package com.tak.restful_api.daos;

import com.tak.restful_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User,Integer> {


    boolean existsById(int id);

    boolean existsByEmail(String email);

    User findByEmail(String email);

}
