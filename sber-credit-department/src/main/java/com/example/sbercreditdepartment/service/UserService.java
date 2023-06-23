package com.example.sbercreditdepartment.service;

import com.example.sbercreditdepartment.repository.UserRepository;
import com.example.sbercreditdepartment.utils.mapper.UserMapper;
import org.springframework.stereotype.Service;

import com.example.sbercreditdepartment.dto.UserDTO;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> getAllClients() {
        return userMapper.toDTOs(userRepository.findAll());
    }

}
