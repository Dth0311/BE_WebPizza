package com.shoppizza.osahaneat.service;

import com.shoppizza.osahaneat.dto.UserDTO;
import com.shoppizza.osahaneat.entity.Users;
import com.shoppizza.osahaneat.repository.UserRepository;
import com.shoppizza.osahaneat.service.imp.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Override
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
}
