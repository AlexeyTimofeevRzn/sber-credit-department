package com.example.sbercreditdepartment.service;

import com.example.sbercreditdepartment.model.CreditContract;
import com.example.sbercreditdepartment.model.User;
import com.example.sbercreditdepartment.repository.CreditContractRepository;
import com.example.sbercreditdepartment.repository.RequestRepository;
import com.example.sbercreditdepartment.utils.logic.DebtAndPaymentCalculator;
import com.example.sbercreditdepartment.utils.mapper.CreditContractMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static com.example.sbercreditdepartment.service.testData.CreditContractTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreditContractServiceTest {
    private final CreditContractRepository creditContractRepository;
    private final RequestRepository requestRepository;
    private final CreditContractMapper creditContractMapper;
    private final DebtAndPaymentCalculator debtAndPaymentCalculator;
    private final CreditContractService creditContractService;

    public CreditContractServiceTest() {
        creditContractRepository = Mockito.mock(CreditContractRepository.class);
        requestRepository = Mockito.mock(RequestRepository.class);
        creditContractMapper = Mockito.mock(CreditContractMapper.class);
        debtAndPaymentCalculator = Mockito.mock(DebtAndPaymentCalculator.class);
        creditContractService = new CreditContractService(creditContractRepository,
                requestRepository, creditContractMapper, debtAndPaymentCalculator);
    }

    @Test
    @Order(1)
    protected void itShouldReturnContract() {
        Mockito.when(creditContractRepository.findById(1)).thenReturn(Optional.of(CREDIT_CONTRACT_1));

        CreditContract creditContract = creditContractService.getContract(1);
        log.info("Testing getContract(id): {}", creditContract);
        assertEquals(creditContract, CREDIT_CONTRACT_1);
    }

    @Test
    @Order(2)
    protected void itShouldReturnContractsBetweenTwoDates() {
        Mockito.when(creditContractRepository.getCreditContractBetweenTwoDates(DATE_1, DATE_2)).thenReturn(CREDIT_CONTRACTS);

        List<CreditContract> contractList = creditContractService.getContractsBetweenTwoDates(TWO_DATES_DTO);
        log.info("Testing getContractsBetweenTwoDates(TwoDatesDTO): {}", contractList);
        assertEquals(contractList, CREDIT_CONTRACTS);
    }

    @Test
    @Order(3)
    protected void itShouldReturnContractsOfManager() {
        Mockito.when(creditContractRepository.getCreditContractByManagerId(1)).thenReturn(CREDIT_CONTRACTS);

        List<CreditContract> contractList = creditContractService.getContractsOfManager(1);
        log.info("Testing getContractsBetweenTwoDates(TwoDatesDTO): {}", contractList);
        assertEquals(contractList, CREDIT_CONTRACTS);
    }
}
