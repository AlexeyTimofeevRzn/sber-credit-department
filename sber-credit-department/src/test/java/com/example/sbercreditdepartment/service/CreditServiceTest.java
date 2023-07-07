package com.example.sbercreditdepartment.service;

import com.example.sbercreditdepartment.dto.CreditDTO;
import com.example.sbercreditdepartment.model.Credit;
import com.example.sbercreditdepartment.repository.CreditRepository;
import com.example.sbercreditdepartment.utils.mapper.CreditMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static com.example.sbercreditdepartment.service.testData.CreditTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreditServiceTest {

    private final CreditRepository creditRepository;
    private final CreditMapper creditMapper;
    private final CreditService creditService;

    public CreditServiceTest() {
        this.creditRepository = Mockito.mock(CreditRepository.class);
        this.creditMapper = Mockito.mock(CreditMapper.class);
        creditService = new CreditService(creditRepository, creditMapper);
    }

    @Test
    @Order(1)
    protected void itShouldReturnAllCredits() {
        Mockito.when(creditRepository.findCreditsNotDeleted()).thenReturn(CREDIT_LIST);
        Mockito.when(creditMapper.toDTOs(CREDIT_LIST)).thenReturn(CREDIT_DTO_LIST);

        List<CreditDTO> dtos = creditService.getAllCredits();
        log.info("Testing getAllCredits(): {}", dtos);
        assertEquals(dtos, CREDIT_DTO_LIST);
    }

    @Test
    @Order(2)
    protected void itShouldReturnOneCredit() {
        Mockito.when(creditRepository.findById(1)).thenReturn(Optional.of(CREDIT_1));
        Mockito.when(creditMapper.toDTO(CREDIT_1)).thenReturn(CREDIT_DTO_1);

        CreditDTO dto = creditService.getOneCredit(1);
        log.info("Testing getOneCredit(id): {}", dto);
        assertEquals(dto, CREDIT_DTO_1);
    }

    @Test
    @Order(3)
    protected void itShouldSoftDeleteCredit() {
        Mockito.when(creditRepository.findById(1)).thenReturn(Optional.of(CREDIT_1));
        Mockito.when(creditRepository.save(CREDIT_1)).thenReturn(CREDIT_1);
        Credit credit = creditService.softDelete(1);
        log.info("Testing softDelete(id): {}", credit.isDeleted());
        assertTrue(credit.isDeleted());
    }
}
