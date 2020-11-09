package com.tak.restful_api.controller;

import com.tak.restful_api.models.Role;
import com.tak.restful_api.service.GlobalService;
import com.tak.restful_api.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    Global global;

    @Autowired
    GlobalService roleService;

    @PostMapping("")
    public Map add(@Valid @RequestBody Role role, Errors errors){

        if(!errors.hasErrors()){
            roleService.save(role);
            return global.FMS(true,"Role Add Success",role.getRole());
        }


        return global.FMS(errors.getFieldError().getDefaultMessage());
    }

}
