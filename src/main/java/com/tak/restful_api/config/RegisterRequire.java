package com.tak.restful_api.config;


import com.tak.restful_api.models.Profile;
import com.tak.restful_api.models.Role;
import com.tak.restful_api.models.User;


public class RegisterRequire {

    private User user;
    private Profile profile;
    private Role role;
    private String token;

    public RegisterRequire() {
    }

    public RegisterRequire(User user, Profile profile, Role role, String token) {
        this.user = user;
        this.profile = profile;
        this.role = role;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
