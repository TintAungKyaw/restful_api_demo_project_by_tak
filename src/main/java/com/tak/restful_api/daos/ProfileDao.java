package com.tak.restful_api.daos;

import com.tak.restful_api.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileDao extends JpaRepository<Profile,Integer> {
}
