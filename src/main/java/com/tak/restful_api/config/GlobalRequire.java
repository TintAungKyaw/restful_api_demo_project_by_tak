package com.tak.restful_api.config;

import org.springframework.stereotype.Service;

@Service
public class GlobalRequire<T> {

    private T user;
    private String token;

    public T getUser() {
        return user;
    }

    public void setUser(T user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
