package com.example.sbercreditdepartment.service;

import com.example.sbercreditdepartment.dto.CreditContractDTO;
import com.example.sbercreditdepartment.dto.ManagerDTO;
import com.example.sbercreditdepartment.dto.UserDTO;
import com.example.sbercreditdepartment.repository.ManagerRepository;
import com.example.sbercreditdepartment.repository.UserRepository;
import com.example.sbercreditdepartment.utils.mapper.CreditContractMapper;
import com.example.sbercreditdepartment.utils.mapper.ManagerMapper;
import com.example.sbercreditdepartment.utils.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static com.example.sbercreditdepartment.service.testData.ManagerTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ManagerServiceTest {

    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;
    private final ManagerMapper managerMapper;
    private final CreditContractMapper creditContractMapper;
    private final UserMapper userMapper;
    private final ManagerService managerService;

    public ManagerServiceTest() {
        managerRepository = Mockito.mock(ManagerRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        managerMapper = Mockito.mock(ManagerMapper.class);
        creditContractMapper = Mockito.mock(CreditContractMapper.class);
        userMapper = Mockito.mock(UserMapper.class);
        managerService = new ManagerService(managerRepository, userRepository, managerMapper, creditContractMapper, userMapper);
    }

    @BeforeEach
    void init() {
        MANAGER_1.setContracts(CREDIT_CONTRACTS);
    }

    @Test
    @Order(1)
    protected void itShouldReturnAllManagers() {
        Mockito.when(managerRepository.findAll()).thenReturn(MANAGER_LIST);
        Mockito.when(managerMapper.toDTOs(MANAGER_LIST)).thenReturn(MANAGER_DTO_LIST);

        List<ManagerDTO> dtos = managerService.getAllManagers();
        log.info("Testing getAllManagers(): {}", dtos);
        assertEquals(dtos, MANAGER_DTO_LIST);
    }

    @Test
    @Order(2)
    protected void itShouldReturnManager() {
        Mockito.when(managerRepository.findById(1)).thenReturn(Optional.of(MANAGER_1));
        Mockito.when(managerMapper.toDTO(MANAGER_1)).thenReturn(MANAGER_DTO_1);

        ManagerDTO dto = managerService.getManager(1);
        log.info("Testing getManager(): {}", dto);
        assertEquals(dto, MANAGER_DTO_1);
    }

    @Test
    @Order(3)
    protected void itShouldReturnContractsOfManager() {
        Mockito.when(managerRepository.findById(1)).thenReturn(Optional.of(MANAGER_1));
        Mockito.when(creditContractMapper.toDTOs(CREDIT_CONTRACTS)).thenReturn(CREDIT_CONTRACT_DTO_LIST);

        List<CreditContractDTO> dtos = managerService.getContractsOfManager(1);
        log.info("Testing getContractsOfManager(id): {}", dtos);
        assertEquals(dtos, CREDIT_CONTRACT_DTO_LIST);
    }

    @Test
    @Order(4)
    protected void itShouldReturnClientsOfManager() {
        Mockito.when(managerRepository.findUserIDSByManagerId(1)).thenReturn(List.of(1));
        Mockito.when(userRepository.findAllById(List.of(1))).thenReturn(USER_LIST);
        Mockito.when(userMapper.toDTOs(USER_LIST)).thenReturn(USER_DTO_LIST);

        List<UserDTO> dtos = managerService.getClientsOfManager(1);
        log.info("Testing getClientsOfManager(id): {}", dtos);
        assertEquals(dtos, USER_DTO_LIST);
    }

}
