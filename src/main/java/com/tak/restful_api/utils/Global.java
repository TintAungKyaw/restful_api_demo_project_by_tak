package com.tak.restful_api.utils;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class Global {


    public Map FMS(boolean con,String message,Object obj){
        Map  msg = new LinkedHashMap();

        msg.put("con",con);
        msg.put("msg",message);
        msg.put("result",obj);

        return msg;
    }


    public Map FMS(String message){
        Map  msg = new LinkedHashMap();

        msg.put("con",false);
        msg.put("msg",message);
        msg.put("result","");

        return msg;
    }


    public Map FMS(boolean con,String message,Object obj,String token){
        Map  msg = new LinkedHashMap();

        msg.put("con",con);
        msg.put("msg",message);
        msg.put("result",obj);
        msg.put("token",token);

        return msg;
    }


}
