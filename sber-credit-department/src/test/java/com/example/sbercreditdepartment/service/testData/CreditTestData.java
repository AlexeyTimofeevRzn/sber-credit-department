package com.example.sbercreditdepartment.service.testData;

import com.example.sbercreditdepartment.dto.CreditDTO;
import com.example.sbercreditdepartment.model.Credit;
import java.util.Arrays;

import java.util.List;

public interface CreditTestData {

    Credit CREDIT_1 =
            new Credit("TEST_CREDIT_1", "DESC_1", 12.9, 100, 200, 60, false, false);
    Credit CREDIT_2 =
            new Credit("TEST_CREDIT_2", "DESC_2", 12.9, 100, 200, 60, false, false);

    Credit CREDIT_3 =
            new Credit("TEST_CREDIT_3", "DESC_3", 12.9, 100, 200, 60, false, false);

    CreditDTO CREDIT_DTO_1 =
            new CreditDTO("TEST_CREDIT_DTO_1", "DESC_1", 12.9, 100, 200, 60, false);
    CreditDTO CREDIT_DTO_2 =
            new CreditDTO("TEST_CREDIT_DTO_2", "DESC_2", 12.9, 100, 200, 60, false);
    CreditDTO CREDIT_DTO_3 =
            new CreditDTO("TEST_CREDIT_DTO_3", "DESC_3", 12.9, 100, 200, 60, false);

    List<Credit> CREDIT_LIST = Arrays.asList(CREDIT_1, CREDIT_2, CREDIT_3);
    List<CreditDTO> CREDIT_DTO_LIST = Arrays.asList(CREDIT_DTO_1, CREDIT_DTO_2, CREDIT_DTO_3);

}
