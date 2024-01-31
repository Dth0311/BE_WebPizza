package com.shoppizza.osahaneat.service.imp;

import com.shoppizza.osahaneat.dto.UserDTO;
import com.shoppizza.osahaneat.entity.Users;
import com.shoppizza.osahaneat.payload.request.SignUpRequest;

import java.util.List;

public interface ILoginService {
    List<UserDTO> getAllUser();
    boolean checkLogin(String userName,String password);
    boolean addUser(SignUpRequest signUpRequest);
}
