package com.example.sbercreditdepartment.service.testData;

import com.example.sbercreditdepartment.dto.ManagerDTO;
import com.example.sbercreditdepartment.dto.TwoDatesDTO;
import com.example.sbercreditdepartment.enums.RequestStatus;
import com.example.sbercreditdepartment.model.Credit;
import com.example.sbercreditdepartment.model.Manager;
import com.example.sbercreditdepartment.model.Request;
import com.example.sbercreditdepartment.model.User;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public interface RequestTestData {

    User USER_1 =
            new User(1, "alex", "alex@mail.ru", "Alex", "Timofeev", null);
    User USER_2 =
            new User(2, "andrew", "andrew@mail.ru", "Andrew", "Mao", null);
    Manager MANAGER_1 =
            new Manager("MANAGER_1", "123", "ANTON_1", "1", "1", null, "1", "1", "1", null, null, null);
    Manager MANAGER_2 =
            new Manager("MANAGER_2", "123", "ANTON_2", "1", "1", null, "1", "1", "1", null, null, null);
    ManagerDTO MANAGER_DTO_1 =
            new ManagerDTO("MANAGER_1", "123", "ANTON_1", "1", "1", null, "1", "1", "1", 1, null);
    ManagerDTO MANAGER_DTO_2 =
            new ManagerDTO("MANAGER_2", "123", "ANTON_2", "1", "1", null, "1", "1", "1", 1, null);
    Credit CREDIT_1 =
            new Credit("TEST_CREDIT_1", "DESC_1", 12.9, 100, 200, 60, false, false);
    Credit CREDIT_2 =
            new Credit("TEST_CREDIT_2", "DESC_2", 12.9, 100, 200, 60, false, false);
    Request REQUEST_1 =
            new Request(USER_1, MANAGER_1, LocalDateTime.of(2023, 2, 1, 1, 1), 1, 1.0, 200, RequestStatus.CONSIDERED, CREDIT_1);
    Request REQUEST_2 =
            new Request(USER_2, MANAGER_2, LocalDateTime.of(2023, 2, 1, 1, 1), 1, 1.0, 200, RequestStatus.CONSIDERED, CREDIT_2);

    List<Request> REQUEST_LIST = Arrays.asList(REQUEST_1, REQUEST_2);

    Date DATE_1 = Date.valueOf("2022-07-23");
    Date DATE_2 = Date.valueOf("2022-07-30");
    TwoDatesDTO TWO_DATES_DTO = new TwoDatesDTO(DATE_1, DATE_2);
}
