package com.example.sbercreditdepartment.service.testData;

import com.example.sbercreditdepartment.dto.CreditContractDTO;
import com.example.sbercreditdepartment.dto.ManagerDTO;
import com.example.sbercreditdepartment.dto.UserDTO;
import com.example.sbercreditdepartment.model.CreditContract;
import com.example.sbercreditdepartment.model.Manager;
import com.example.sbercreditdepartment.model.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface ManagerTestData {

    Manager MANAGER_1 =
            new Manager("MANAGER_1", "123", "ANTON_1", "1", "1", null, "1", "1", "1", null, null, null);
    Manager MANAGER_2 =
            new Manager("MANAGER_2", "123", "ANTON_2", "1", "1", null, "1", "1", "1", null, null, null);
    Manager MANAGER_3 =
            new Manager("MANAGER_3", "123", "ANTON_3", "1", "1", null, "1", "1", "1", null, null, null);
    ManagerDTO MANAGER_DTO_1 =
            new ManagerDTO("MANAGER_1", "123", "ANTON_1", "1", "1", null, "1", "1", "1", 1, null);
    ManagerDTO MANAGER_DTO_2 =
            new ManagerDTO("MANAGER_2", "123", "ANTON_2", "1", "1", null, "1", "1", "1", 1, null);
    ManagerDTO MANAGER_DTO_3 =
            new ManagerDTO("MANAGER_3", "123", "ANTON_3", "1", "1", null, "1", "1", "1", 1, null);

    List<ManagerDTO> MANAGER_DTO_LIST = Arrays.asList(MANAGER_DTO_1, MANAGER_DTO_2, MANAGER_DTO_3);
    List<Manager> MANAGER_LIST = Arrays.asList(MANAGER_1, MANAGER_2, MANAGER_3);
    User USER_1 =
            new User(1, "alex", "alex@mail.ru", "Alex", "Timofeev", null);
    UserDTO USER_DTO_1 =
            new UserDTO(1, "alex", "alex@mail.ru", "Alex", "Timofeev", new ArrayList<>());

    CreditContract CREDIT_CONTRACT_1 = new CreditContract(null, USER_1, Date.valueOf("2021-07-07"), null, 12, 120000.0, 1000000.0, MANAGER_1);
    CreditContract CREDIT_CONTRACT_2 = new CreditContract(null, USER_1, Date.valueOf("2021-07-07"), null, 36, 120000.0, 1000000.0, MANAGER_1);
    CreditContractDTO CREDIT_CONTRACT_DTO_1 = new CreditContractDTO(1, 1, 1,Date.valueOf("2021-07-07"), 12, 120000.0, 1000000.0, 1);
    CreditContractDTO CREDIT_CONTRACT_DTO_2 = new CreditContractDTO(1, 1, 1, Date.valueOf("2021-07-07"), 36, 120000.0, 1000000.0, 1);

    List<CreditContract> CREDIT_CONTRACTS = Arrays.asList(CREDIT_CONTRACT_1, CREDIT_CONTRACT_2);
    List<CreditContractDTO> CREDIT_CONTRACT_DTO_LIST = Arrays.asList(CREDIT_CONTRACT_DTO_1, CREDIT_CONTRACT_DTO_2);

    List<User> USER_LIST = Arrays.asList(USER_1);
    List<UserDTO> USER_DTO_LIST = Arrays.asList(USER_DTO_1);
}
