package com.tak.restful_api.service;

import com.tak.restful_api.daos.ProfileDao;
import com.tak.restful_api.models.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService implements GlobalService<Profile>{

    @Autowired
    ProfileDao profileDao;

    @Override
    public Profile save(Profile obj) {
        return profileDao.save(obj);
    }

    @Override
    public List<Profile> getAll() {
        return null;
    }

    @Override
    public Profile get(int id) {
        return null;
    }

    @Override
    public void delete(int id) {
        profileDao.deleteById(id);
    }

    @Override
    public Profile update(Profile obj) {
        return null;
    }
}
