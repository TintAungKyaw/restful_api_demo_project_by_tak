package com.tak.restful_api.service;

import com.tak.restful_api.daos.UserDao;
import com.tak.restful_api.models.User;
import com.tak.restful_api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements GlobalService<User>{

    @Autowired
    UserDao userDao;

    @Autowired
    Utils utils;

    @Override
    public User save(User obj) {
        obj.setPassword(utils.encodePasswod(obj.getPassword()));
        return userDao.save(obj);
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public User get(int id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        userDao.deleteById(id);
    }

    @Override
    public User update(User obj) {
        obj.setPassword(utils.encodePasswod(obj.getPassword()));
        return userDao.save(obj);
    }
}
