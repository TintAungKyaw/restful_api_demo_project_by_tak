package com.tak.restful_api.controller;

import com.tak.restful_api.config.RegisterRequire;
import com.tak.restful_api.daos.RoleDao;
import com.tak.restful_api.daos.UserDao;
import com.tak.restful_api.models.Profile;
import com.tak.restful_api.models.Role;
import com.tak.restful_api.models.User;
import com.tak.restful_api.service.GlobalService;
import com.tak.restful_api.utils.Global;
import com.tak.restful_api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    Global global;

    @Autowired
    GlobalService userService,profileService,roleService;

    @Autowired
    RoleDao roleDao;

    @Autowired
    UserDao userDao;

    @Autowired
    Utils utils;

    @PostMapping("")
    public Map add(@Valid @RequestBody RegisterRequire require, Errors errors){

        User user = require.getUser();
        Profile profile = require.getProfile();
        Role role = roleDao.findByRole(require.getRole().getRole());

        if(!errors.hasErrors() && role != null){
            /** Profile */
            profileService.save(profile);

            /** User */
            user.setProfile(profile);
            user.setRole(role);
            userService.save(user);

            /** Role */
            role.getUsers().add(user);
            roleService.save(role);
            return global.FMS(true,"User Success",require);
        }
        return global.FMS(errors.getFieldError().getDefaultMessage());
    }


    @PostMapping("/login")
    public Map login(@Valid @RequestBody User user,Errors errors){
        if(userDao.existsByEmail(user.getEmail())){
            User getUser = userDao.findByEmail(user.getEmail());
            boolean valPass = utils.matchPassword(user.getPassword(),getUser.getPassword());

            if(valPass){
                String userToken = utils.createToken(getUser.getEmail());
//                System.out.println("Token data >> "+utils.getFromToken(userToken));
                return global.FMS(true,"Login Success",getUser,userToken);
            }
            else return global.FMS("Login Fail");
        }

        return global.FMS("Login Fail");
    }

    @PostMapping("/{id}")
    public Map get(@PathVariable int id,@RequestBody Map<String,String> body){

        if(utils.validToken(body.get("token"))) return global.FMS(true,"Get User",userService.get(id));

        return global.FMS("Get User Fail");
    }

//    @PostMapping("/all")
//    public Map getAll(@RequestBody Map<String,String> body){
//
//        boolean validToken = utils.validToken(body.get("token"));
//
//        if(validToken) return global.FMS(true,"Get All User",userService.getAll());
//
//        return global.FMS("Get All User Fail");
//    }


    @GetMapping("/all")
    public Map getAllUser(HttpServletRequest request){

        String token = request.getHeader("Authorization").replace("Bearer ","");

        boolean validToken = utils.validToken(token);

        if(validToken) return global.FMS(true,"Get All User",userService.getAll());


        return global.FMS("Get All User Fail");
    }

    /* Check Token */
    @PatchMapping("/{id}")
    public Map update(@PathVariable int id,@RequestBody RegisterRequire require){
        if(userDao.existsById(id)) {
            Role updateRole = roleDao.findByRole(require.getRole().getRole());
            User getUser = (User) userService.get(id);
            getUser.getRole().getUsers().remove(getUser);
            userService.update(getUser);
            getUser.setEmail(require.getUser().getEmail());
            getUser.setPassword(require.getUser().getPassword());
            getUser.getProfile().setName(require.getProfile().getName());
            getUser.getProfile().setPhone(require.getProfile().getPhone());
            getUser.setRole(updateRole);
            userService.update(getUser);
            updateRole.getUsers().add(getUser);
            roleService.update(updateRole);
            return global.FMS(true,"Update User Success","");
        }

        return global.FMS("Update Fail");
    }

    @DeleteMapping("/{id}")
    public Map delete(@PathVariable int id,@RequestBody Map<String,String> body){

        boolean isUser = userDao.existsById(id);
        String token = body.get("token");

        if(isUser && utils.validToken(token)){
            User getUser = (User) userService.get(id);
            int profileID = getUser.getProfile().getId();
            /** role_user_set Delete */
            getUser.getRole().getUsers().remove(getUser);
            userService.update(getUser);

            /** Profile Delete*/
            profileService.delete(profileID);

            /** User Delete*/
            userService.delete(id);
            return global.FMS(true,"Delete Success","");
        }
        return global.FMS("Delete Fail");
    }
}