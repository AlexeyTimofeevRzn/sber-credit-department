package com.example.sbercreditdepartment.service.testData;

import com.example.sbercreditdepartment.dto.CreditContractDTO;
import com.example.sbercreditdepartment.dto.UserDTO;
import com.example.sbercreditdepartment.model.CreditContract;
import com.example.sbercreditdepartment.model.User;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface UserTestData {

    UserDTO USER_DTO_1 =
            new UserDTO(1, "alex", "alex@mail.ru", "Alex", "Timofeev", new ArrayList<>());

    UserDTO USER_DTO_2 =
            new UserDTO(2, "andrew", "andrew@mail.ru", "Andrew", "Mao", new ArrayList<>());

    UserDTO USER_DTO_3 =
            new UserDTO(3, "liner", "liner@mail.ru", "Liner", "Dao", new ArrayList<>());

    User USER_1 =
            new User(1, "alex", "alex@mail.ru", "Alex", "Timofeev", null);

    User USER_2 =
            new User(2, "andrew", "andrew@mail.ru", "Andrew", "Mao", null);

    User USER_3 =
            new User(3, "liner", "liner@mail.ru", "Liner", "Dao", null);

    List<UserDTO> USER_DTO_LIST = Arrays.asList(USER_DTO_1, USER_DTO_2, USER_DTO_3);

    String TEST_LOGIN_1 = "alex";
    String TEST_EMAIL_1 = "alex@mail.ru";

    CreditContract CREDIT_CONTRACT_1 = new CreditContract(null, USER_1, Date.valueOf("2021-07-07"), null, 12, 120000.0, 1000000.0, null);
    CreditContract CREDIT_CONTRACT_2 = new CreditContract(null, USER_1, Date.valueOf("2021-07-07"), null, 36, 120000.0, 1000000.0, null);
    CreditContractDTO CREDIT_CONTRACT_DTO_1 = new CreditContractDTO(1, 1, 1,Date.valueOf("2021-07-07"), 12, 120000.0, 1000000.0, 1);
    CreditContractDTO CREDIT_CONTRACT_DTO_2 = new CreditContractDTO(1, 1, 1, Date.valueOf("2021-07-07"), 36, 120000.0, 1000000.0, 1);

    List<CreditContract> CREDIT_CONTRACTS_LIST = Arrays.asList(CREDIT_CONTRACT_1, CREDIT_CONTRACT_2);

    List<CreditContractDTO> CREDIT_CONTRACT_DTOS = Arrays.asList(CREDIT_CONTRACT_DTO_1, CREDIT_CONTRACT_DTO_2);

    List<User> USER_LIST = Arrays.asList(USER_1, USER_2, USER_3);

}
