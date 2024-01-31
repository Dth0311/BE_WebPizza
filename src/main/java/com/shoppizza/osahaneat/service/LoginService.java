package com.shoppizza.osahaneat.service;

import com.shoppizza.osahaneat.dto.UserDTO;
import com.shoppizza.osahaneat.entity.Roles;
import com.shoppizza.osahaneat.entity.Users;
import com.shoppizza.osahaneat.payload.request.SignUpRequest;
import com.shoppizza.osahaneat.repository.UserRepository;
import com.shoppizza.osahaneat.service.imp.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements ILoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDTO> getAllUser() {
        List<Users> listUser = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (var user : listUser) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUserName(user.getUserName());
            userDTO.setPassword(user.getPassword());
            userDTO.setFullName(user.getFullName());
            userDTO.setCreateDate(user.getCreateDate());
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public boolean checkLogin(String userName, String password) {
        Users users = userRepository.findByUserName(userName);
        return passwordEncoder.matches(password,users.getPassword());
    }

    @Override
    public boolean addUser(SignUpRequest signUpRequest) {
        Roles roles = new Roles();
        roles.setId(signUpRequest.getRoleId());
        Users users = new Users();
        users.setFullName(signUpRequest.getFullname());
        users.setUserName(signUpRequest.getEmail());
        users.setRoles(roles);
        String password = signUpRequest.getPassword();
        String encodePas = passwordEncoder.encode(password);
        users.setPassword(encodePas);
        try {
            userRepository.save(users);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
