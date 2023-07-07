package com.example.sbercreditdepartment.service;

import com.example.sbercreditdepartment.dto.CreditContractDTO;
import com.example.sbercreditdepartment.dto.RequestDTO;
import com.example.sbercreditdepartment.exception.UserNotFoundException;
import com.example.sbercreditdepartment.model.Request;
import com.example.sbercreditdepartment.model.User;
import com.example.sbercreditdepartment.repository.RequestRepository;
import com.example.sbercreditdepartment.repository.UserRepository;
import com.example.sbercreditdepartment.utils.mapper.CreditContractMapper;
import com.example.sbercreditdepartment.utils.mapper.RequestMapper;
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
    private final RequestRepository requestRepository;


    public UserService(UserRepository userRepository, UserMapper userMapper, CreditContractMapper creditContractMapper, BCryptPasswordEncoder bCryptPasswordEncoder, RequestRepository requestRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.creditContractMapper = creditContractMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.requestRepository = requestRepository;
    }

    public UserDTO getClient(int id) {
        return userMapper.toDTO(userRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    public User create(UserDTO newObject) {
        newObject.setRoleId(1);
        newObject.setPassword(bCryptPasswordEncoder.encode(newObject.getPassword()));
        newObject.setCreatedBy("REGISTRATION FORM");
        newObject.setCreatedWhen(LocalDateTime.now());
        return userRepository.save(userMapper.toEntity(newObject));
    }

    public List<CreditContractDTO> getCreditContractsOfUser(int id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return creditContractMapper.toDTOs(user.getContracts());
    }

    public List<Request> getActiveRequestsOfUser(int id) {
        return requestRepository.findRequestByUserIdAndNotSigned(id);
    }

    public User getUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public List<UserDTO> getAllClients() {
        return userMapper.toDTOs(userRepository.findAll());
    }

}
