package com.tak.restful_api.service;

import java.util.List;

public interface GlobalService<T> {

    T save(T obj);

    List<T> getAll();

    T get(int id);

    void delete(int id);

    T update(T obj);

}
