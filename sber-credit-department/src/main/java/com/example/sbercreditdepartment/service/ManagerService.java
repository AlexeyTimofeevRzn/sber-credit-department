package com.example.sbercreditdepartment.service;

import com.example.sbercreditdepartment.dto.CreditContractDTO;
import com.example.sbercreditdepartment.dto.ManagerDTO;
import com.example.sbercreditdepartment.exception.ManagerNotFoundException;
import com.example.sbercreditdepartment.model.Manager;
import com.example.sbercreditdepartment.dto.UserDTO;
import com.example.sbercreditdepartment.repository.ManagerRepository;
import com.example.sbercreditdepartment.repository.UserRepository;
import com.example.sbercreditdepartment.utils.mapper.CreditContractMapper;
import com.example.sbercreditdepartment.utils.mapper.ManagerMapper;
import com.example.sbercreditdepartment.utils.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;
    private final ManagerMapper managerMapper;
    private final CreditContractMapper creditContractMapper;
    private final UserMapper userMapper;

    public ManagerService(ManagerRepository managerRepository, UserRepository userRepository, ManagerMapper managerMapper, CreditContractMapper creditContractMapper, UserMapper userMapper) {
        this.managerRepository = managerRepository;
        this.userRepository = userRepository;
        this.managerMapper = managerMapper;
        this.creditContractMapper = creditContractMapper;
        this.userMapper = userMapper;
    }

    public List<ManagerDTO> getAllManagers() {
        return managerMapper.toDTOs(managerRepository.findAll());
    }

    public ManagerDTO getManager(int id) {
        return managerMapper.toDTO(managerRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    public List<CreditContractDTO> getContractsOfManager(int id) {
        Manager manager = managerRepository.findById(id).orElseThrow(ManagerNotFoundException::new);
        return creditContractMapper.toDTOs(manager.getContracts());
    }

    public List<UserDTO> getClientsOfManager(int managerId) {
        List<Integer> ids = managerRepository.findUserIDSByManagerId(managerId);
        return userMapper.toDTOs(userRepository.findAllById(ids));
    }
}
