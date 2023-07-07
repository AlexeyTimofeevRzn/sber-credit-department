package com.example.sbercreditdepartment.service;

import com.example.sbercreditdepartment.enums.RequestStatus;
import com.example.sbercreditdepartment.model.Request;
import com.example.sbercreditdepartment.repository.CreditRepository;
import com.example.sbercreditdepartment.repository.RequestRepository;
import com.example.sbercreditdepartment.repository.UserRepository;
import com.example.sbercreditdepartment.service.testData.RequestTestData;
import com.example.sbercreditdepartment.utils.logic.DebtAndPaymentCalculator;
import com.example.sbercreditdepartment.utils.mapper.ManagerMapper;
import com.example.sbercreditdepartment.utils.mapper.RequestMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static com.example.sbercreditdepartment.service.testData.CreditContractTestData.CREDIT_CONTRACTS;
import static com.example.sbercreditdepartment.service.testData.RequestTestData.*;
import static com.example.sbercreditdepartment.service.testData.UserTestData.USER_DTO_1;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RequestServiceTest {
    private final RequestRepository requestRepository;
    private final CreditRepository creditRepository;
    private final UserRepository userRepository;
    private final RequestMapper requestMapper;
    private final ManagerMapper managerMapper;
    private final DebtAndPaymentCalculator debtAndPaymentCalculator;
    private final RequestService requestService;

    public RequestServiceTest() {
        requestRepository = Mockito.mock(RequestRepository.class);
        creditRepository = Mockito.mock(CreditRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        requestMapper = Mockito.mock(RequestMapper.class);
        managerMapper = Mockito.mock(ManagerMapper.class);
        debtAndPaymentCalculator = Mockito.mock(DebtAndPaymentCalculator.class);
        requestService =
                new RequestService(requestRepository, creditRepository, userRepository, requestMapper,
                        managerMapper, debtAndPaymentCalculator);
    }

    @Test
    @Order(1)
    protected void itShouldReturnRequests() {
        Mockito.when(requestRepository.findRequestNotSigned()).thenReturn(REQUEST_LIST);

        List<Request> requests = requestService.getAllRequests();
        log.info("Testing getAllRequests(): {}", requests);
        assertEquals(requests, REQUEST_LIST);
    }

    @Test
    @Order(2)
    protected void itShouldReturnOneRequest() {
        Mockito.when(requestRepository.findById(1)).thenReturn(Optional.of(REQUEST_1));

        Request request = requestService.getOneRequest(1);
        log.info("Testing getOneRequest(id): {}", request);
        assertEquals(request, REQUEST_1);
    }

    @Test
    @Order(3)
    protected void itShouldAcceptRequest() {
        Mockito.when(managerMapper.toEntity(MANAGER_DTO_1)).thenReturn(MANAGER_1);
        Mockito.when(requestRepository.save(REQUEST_1)).thenReturn(REQUEST_1);

        Request request = requestService.acceptRequest(REQUEST_1, MANAGER_DTO_1);
        log.info("Testing acceptRequest(request, manager): {}", request);
        assertEquals(request.getManager(), MANAGER_1);
        assertEquals(request.getRequestStatus(), RequestStatus.ACCEPTED);
    }

    @Test
    @Order(4)
    protected void itShouldDeclineRequest() {
        Mockito.when(requestRepository.save(REQUEST_1)).thenReturn(REQUEST_1);

        Request request = requestService.declineRequest(REQUEST_1);
        log.info("Testing acceptRequest(request, manager): {}", request);
        assertEquals(request.getRequestStatus(), RequestStatus.DECLINED);
    }

    @Test
    @Order(5)
    protected void itShouldReturnRequestsBetweenTwoDates() {
        Mockito.when(requestRepository.requestsBetweenTwoDates(DATE_1, DATE_2)).thenReturn(REQUEST_LIST);

        List<Request> requests = requestService.getRequestsBetweenTwoDates(TWO_DATES_DTO);
        log.info("Testing getRequestsBetweenTwoDates(TwoDatesDTO): {}", requests);
        assertEquals(requests, REQUEST_LIST);
    }

}
