package com.example.sbercreditdepartment.service;

import com.example.sbercreditdepartment.dto.CreditContractDTO;
import com.example.sbercreditdepartment.model.User;
import com.example.sbercreditdepartment.repository.UserRepository;
import com.example.sbercreditdepartment.utils.mapper.CreditContractMapper;
import com.example.sbercreditdepartment.utils.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.sbercreditdepartment.dto.UserDTO;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CreditContractMapper creditContractMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserService(UserRepository userRepository, UserMapper userMapper, CreditContractMapper creditContractMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.creditContractMapper = creditContractMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserDTO getClient(int id) {
        return userMapper.toDTO(userRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    public void create(UserDTO newObject) {
        newObject.setRoleId(1);
        newObject.setClientStatus(1);
        newObject.setPassword(bCryptPasswordEncoder.encode(newObject.getPassword()));
        newObject.setCreatedBy("REGISTRATION FORM");
        newObject.setCreatedWhen(LocalDateTime.now());
        userRepository.save(userMapper.toEntity(newObject));
    }

    public List<CreditContractDTO> getCreditContractsByUserId(int id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        return creditContractMapper.toDTOs(user.getContracts());
    }

    public List<UserDTO> getAllClients() {
        return userMapper.toDTOs(userRepository.findAll());
    }

}
