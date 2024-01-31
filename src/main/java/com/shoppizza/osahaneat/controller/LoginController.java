package com.shoppizza.osahaneat.controller;

import com.shoppizza.osahaneat.dto.UserDTO;
import com.shoppizza.osahaneat.entity.Users;
import com.shoppizza.osahaneat.payload.ResponseData;
import com.shoppizza.osahaneat.payload.request.SignUpRequest;
import com.shoppizza.osahaneat.repository.UserRepository;
import com.shoppizza.osahaneat.service.LoginService;
import com.shoppizza.osahaneat.utils.JwtUtilsHelper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private JwtUtilsHelper jwtUtilsHelper;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String userName, @RequestParam String password) {
        ResponseData responseData = new ResponseData();
        if(loginService.checkLogin(userName,password)){
            String token = jwtUtilsHelper.generateToken(userName);
            responseData.setData(token);
        }else {
            responseData.setSuccess(false);
            responseData.setData("");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> sigup(@RequestBody SignUpRequest signUpRequest){
        ResponseData responseData = new ResponseData();
        responseData.setData(loginService.addUser(signUpRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
