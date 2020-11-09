package com.tak.restful_api.service;

import com.tak.restful_api.daos.RoleDao;
import com.tak.restful_api.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements GlobalService<Role>{

    @Autowired
    RoleDao roleDao;

    @Override
    public Role save(Role obj) {
        return roleDao.save(obj);
    }

    @Override
    public List<Role> getAll() {
        return null;
    }

    @Override
    public Role get(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Role update(Role obj) {
        return roleDao.save(obj);
    }

}
