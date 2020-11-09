package com.tak.restful_api.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;

@Service
public class Utils {

    final String secretKey = "admin123!";

    BCryptPasswordEncoder bc = new BCryptPasswordEncoder();

    public String encodePasswod(String password){

        String encodePass = bc.encode(password);

        return encodePass;
    }


    public boolean matchPassword(String plainPass,String encodePass){

        boolean matchPass = bc.matches(plainPass,encodePass);

        return matchPass;
    }


    public String createToken(String subject){

        String token = Jwts.builder()
                    .setSubject(subject)
                    .setIssuedAt(new Date())
                    .setExpiration(Date.from(ZonedDateTime.now().plusHours(1).toInstant()))
                    .signWith(SignatureAlgorithm.HS256,secretKey)
                    .compact();


        return token;
    }

    public String getFromToken(String token){

        String getForm = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();

        return getForm;
    }


    public boolean validToken(String token){

        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            System.out.println("Token "+e);
        }


        return false;
    }




}
