package com.example.sbercreditdepartment.service;

import com.example.sbercreditdepartment.dto.CreditContractDTO;
import com.example.sbercreditdepartment.dto.UserDTO;
import com.example.sbercreditdepartment.model.User;
import com.example.sbercreditdepartment.repository.RequestRepository;
import com.example.sbercreditdepartment.repository.UserRepository;
import com.example.sbercreditdepartment.utils.mapper.CreditContractMapper;
import com.example.sbercreditdepartment.utils.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static com.example.sbercreditdepartment.service.testData.UserTestData.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CreditContractMapper creditContractMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RequestRepository requestRepository;
    private final UserService userService;

    public UserServiceTest() {
        userRepository = Mockito.mock(UserRepository.class);
        userMapper = Mockito.mock(UserMapper.class);
        creditContractMapper = Mockito.mock(CreditContractMapper.class);
        bCryptPasswordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
        requestRepository = Mockito.mock(RequestRepository.class);
        userService = new UserService
                (userRepository, userMapper,
                        creditContractMapper,
                        bCryptPasswordEncoder, requestRepository);
    }

    @BeforeEach
    void init() {
        USER_1.setContracts(CREDIT_CONTRACTS_LIST);
    }

    @Test
    @Order(1)
    protected void itShouldReturnOneUser() {
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(USER_1));
        Mockito.when(userMapper.toDTO(USER_1)).thenReturn(USER_DTO_1);

        UserDTO userDTO = userService.getClient(1);
        log.info("Testing getClient(): {}", userDTO);
        assertEquals(userDTO, USER_DTO_1);
    }

    @Test
    @Order(2)
    protected void itShouldReturnListOfUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(USER_LIST);
        Mockito.when(userMapper.toDTOs(USER_LIST)).thenReturn(USER_DTO_LIST);

        List<UserDTO> userDTOs = userService.getAllClients();
        log.info("Testing getAllClients(): {}", userDTOs);
        assertEquals(userDTOs, USER_DTO_LIST);
    }

    @Test
    @Order(3)
    protected void itShouldCreateUser() {
        Mockito.when(userMapper.toEntity(USER_DTO_1)).thenReturn(USER_1);
        Mockito.when(userRepository.save(USER_1)).thenReturn(USER_1);

        User savedUser = userService.create(USER_DTO_1);
        log.info("Testing create(): {}", savedUser);
        assertEquals(USER_1, savedUser);
    }

    @Test
    @Order(4)
    protected void itShouldReturnUserByLogin() {
        Mockito.when(userRepository.findUserByLogin(TEST_LOGIN_1)).thenReturn(USER_1);

        User user = userService.getUserByLogin(TEST_LOGIN_1);
        log.info("Testing getUserByLogin(): {}", user);
        assertEquals(USER_1, user);
    }

    @Test
    @Order(5)
    protected void itShouldReturnUserByEmail() {
        Mockito.when(userRepository.findUserByEmail(TEST_EMAIL_1)).thenReturn(USER_1);

        User user = userService.getUserByEmail(TEST_EMAIL_1);
        log.info("Testing getUserByEmail(): {}", user);
        assertEquals(user, USER_1);
    }

    @Test
    @Order(6)
    protected void itShouldReturnListOfCreditContracts() {
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(USER_1));
        Mockito.when(creditContractMapper.toDTOs(CREDIT_CONTRACTS_LIST)).thenReturn(CREDIT_CONTRACT_DTOS);

        List<CreditContractDTO> dtos = userService.getCreditContractsOfUser(1);
        log.info("Testing getCreditContractsOfUser(): {}", dtos);
        assertEquals(dtos, CREDIT_CONTRACT_DTOS);
    }
}
